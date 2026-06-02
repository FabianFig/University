
import java.util.*;

// AST checks
// implements five required checks:
// 1. duplicate job names
// 2. undefined job references in needs
// 3. invalid trigger events
// 4. cycle detection in job dependency graph
// 5. dangerous run commands

public class SemanticAnalyser {

    public static class Finding {

        public final boolean error;
        public final SourceLocation location;
        public final String message;

        public Finding(boolean error, SourceLocation location, String message) {
            this.error = error;
            this.location = location;
            this.message = message;
        }

        @Override
        public String toString() {
            String prefix = error ? "ERROR" : "WARNING";
            String locStr = location != null ? location.toString() : "unknown";
            return prefix + ": " + locStr + ": " + message;
        }
    }

    private final List<Finding> findings = new ArrayList<>();

    // valid github actions trigger events
    private static final Set<String> VALID_EVENTS = new HashSet<>(Arrays.asList(
            "push", "pull_request", "workflow_dispatch", "schedule",
            "issues", "issue_comment", "pull_request_review", "pull_request_review_comment",
            "release", "create", "delete", "fork", "gollum", "page_build",
            "public", "repository", "watch"
    ));

    // dangerous command patterns to warn with
    private static final String[] DANGEROUS_PATTERNS = {
        "rm -rf", "mkfs", "dd if=", "fdisk", "shred", "format", ":/dev/"
    };

    public SemanticAnalyser() {
    }

    public List<Finding> getFindings() {
        return findings;
    }

    public boolean hasErrors() {
        for (Finding finding : findings) {
            if (finding.error) {
                return true;
            }
        }
        return false;
    }

    private void addError(SourceLocation loc, String message) {
        findings.add(new Finding(true, loc, message));
    }

    private void addWarning(SourceLocation loc, String message) {
        findings.add(new Finding(false, loc, message));
    }

    // run all semantic checks on the workflow ast
    public void analyze(WorkflowNode workflow) {
        if (workflow == null) {
            return;
        }

        // check 1: duplicate job names
        checkDuplicateJobNames(workflow);

        // check 2: invalid trigger events
        checkValidTriggerEvents(workflow);

        // check 3: undefined job references in needs
        checkUndefinedJobReferences(workflow);

        // check 4: cycle detection in job dependency graph
        checkJobDependencyCycles(workflow);

        // check 5: dangerous run commands
        checkDangerousRunCommands(workflow);
    }

    // duplicate job names
    private void checkDuplicateJobNames(WorkflowNode workflow) {
        Set<String> seenNames = new HashSet<>();
        for (JobNode job : workflow.jobs) {
            if (seenNames.contains(job.name)) {
                addError(
                        job.getLocation(),
                        "Duplicate job name: \"" + job.name + "\""
                );
            } else {
                seenNames.add(job.name);
            }
        }
    }

    // invalid trigger events
    private void checkValidTriggerEvents(WorkflowNode workflow) {
        for (String event : workflow.events) {
            if (!VALID_EVENTS.contains(event)) {
                addWarning(
                        workflow.getLocation(),
                        "Unknown trigger event: \"" + event + "\". Valid events are: "
                        + String.join(", ", VALID_EVENTS)
                );
            }
        }
    }

    // undefined job references in needs
    private void checkUndefinedJobReferences(WorkflowNode workflow) {
        Set<String> jobNames = workflow.getJobNames();
        for (JobNode job : workflow.jobs) {
            for (String dependency : job.getDependencies()) {
                if (!jobNames.contains(dependency)) {
                    addError(
                            job.getLocation(),
                            "Job \"" + job.name + "\" references undefined job in needs: \"" + dependency + "\""
                    );
                }
            }
        }
    }

    // cycle detection in job dependency graph
    private void checkJobDependencyCycles(WorkflowNode workflow) {
        // build adjacncy map: job -> list of dependencies
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, JobNode> jobMap = new HashMap<>();
        for (JobNode job : workflow.jobs) {
            graph.put(job.name, job.getDependencies());
            jobMap.put(job.name, job);
        }

        // dfs cycle detection using 3-colour marking:
        // 0 = white (unvisited), 1 = grey (visiting), 2 = black (done)
        Map<String, Integer> colour = new HashMap<>();
        Map<String, String> parent = new HashMap<>();

        for (String job : graph.keySet()) {
            if (!colour.containsKey(job)) {
                colour.put(job, 0);
            }
        }

        for (String job : graph.keySet()) {
            if (colour.get(job) == 0) {
                List<String> cycle = detectCycleDFS(job, graph, colour, parent);
                if (cycle != null) {
                    JobNode jobNode = jobMap.get(job);
                    String cycleStr = String.join(" -> ", cycle) + " -> " + cycle.get(0);
                    addError(
                            jobNode.getLocation(),
                            "Circular dependency detected: " + cycleStr
                    );
                }
            }
        }
    }

    // dfs helper for cycle detection
    private List<String> detectCycleDFS(String node, Map<String, List<String>> graph,
            Map<String, Integer> colour, Map<String, String> parent) {
        colour.put(node, 1);  // grey

        List<String> deps = graph.getOrDefault(node, new ArrayList<>());
        for (String neighbor : deps) {
            if (!colour.containsKey(neighbor)) {
                colour.put(neighbor, 0);
            }

            if (colour.get(neighbor) == 0) {
                parent.put(neighbor, node);
                List<String> cycle = detectCycleDFS(neighbor, graph, colour, parent);
                if (cycle != null) {
                    return cycle;
                }
                // TODO: I think I need to fix this. Seems to be printing backwards?
            } else if (colour.get(neighbor) == 1) {
                // back edge found; reconstruct cycle
                List<String> cycle = new ArrayList<>();
                cycle.add(neighbor);
                String current = node;
                while (!current.equals(neighbor)) {
                    cycle.add(current);
                    current = parent.getOrDefault(current, current);
                }
                return cycle;
            }
        }

        colour.put(node, 2);  // black
        return null;
    }

    // dangerous run commands
    private void checkDangerousRunCommands(WorkflowNode workflow) {
        for (JobNode job : workflow.jobs) {
            for (StepNode step : job.steps) {
                for (String pattern : DANGEROUS_PATTERNS) {
                    if (step.command.toLowerCase().contains(pattern.toLowerCase())) {
                        addWarning(
                                step.getLocation(),
                                "Potentially dangerous command detected: \"" + step.command + "\" contains \"" + pattern + "\""
                        );
                        break;  // warn once per step
                    }
                }
            }
        }
    }
}
