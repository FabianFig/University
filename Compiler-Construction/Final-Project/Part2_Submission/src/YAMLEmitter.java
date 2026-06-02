
public class YAMLEmitter {

    public String emit(WorkflowNode workflow) {
        StringBuilder sb = new StringBuilder();

        // name: <workflow name>
        sb.append("name: ").append(workflow.name).append("\n");

        // on: push  or  on: [push, pull_request]
        if (workflow.events.size() == 1) {
            sb.append("on: ").append(workflow.events.get(0)).append("\n");
        } else {
            sb.append("on: [").append(String.join(", ", workflow.events)).append("]\n");
        }

        sb.append("jobs:\n");

        for (JobNode job : workflow.jobs) {
            // github actions uses the job name as a yaml key:
            // lowercase, spaces replaced with hyphens
            String jobId = job.name.toLowerCase().replace(" ", "-");
            sb.append("  ").append(jobId).append(":\n");

            // runs-on (note: dsl uses runs_on, yaml uses runs-on)
            sb.append("    runs-on: ").append(job.runsOn).append("\n");

            // needs: only emit if present
            if (!job.needs.isEmpty()) {
                if (job.needs.size() == 1) {
                    sb.append("    needs: ").append(job.needs.get(0)).append("\n");
                } else {
                    sb.append("    needs: [")
                            .append(String.join(", ", job.needs))
                            .append("]\n");
                }
            }

            // steps
            sb.append("    steps:\n");
            for (StepNode step : job.steps) {
                sb.append("      - run: ").append(step.command).append("\n");
            }
        }

        return sb.toString();
    }
}
