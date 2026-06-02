
import java.util.*;

// represents a job in the workflow ast
public class JobNode extends ASTNode {

    public String name;
    public List<String> needs;
    public String runsOn;
    public List<StepNode> steps;

    public JobNode(SourceLocation loc, String name, List<String> needs, String runsOn, List<StepNode> steps) {
        super(loc);
        this.name = name;
        this.needs = needs != null ? needs : new ArrayList<>();
        this.runsOn = runsOn;
        this.steps = steps;
    }

    // get job dependencies
    public List<String> getDependencies() {
        return needs;
    }
}
