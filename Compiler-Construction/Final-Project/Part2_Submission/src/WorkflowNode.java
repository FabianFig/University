
import java.util.*;

// top-level ast node for a workflow
public class WorkflowNode extends ASTNode {

    public String name;
    public List<String> events;
    public List<JobNode> jobs;

    public WorkflowNode(SourceLocation loc, String name, List<String> events, List<JobNode> jobs) {
        super(loc);
        this.name = name;
        this.events = events;
        this.jobs = jobs;
    }

    // find a job by name
    public JobNode findJobByName(String jobName) {
        for (JobNode job : jobs) {
            if (job.name.equals(jobName)) {
                return job;
            }
        }
        return null;
    }

    // get all job names
    public Set<String> getJobNames() {
        Set<String> names = new HashSet<>();
        for (JobNode job : jobs) {
            names.add(job.name);
        }
        return names;
    }
}
