// represents a single run step in a job

public class StepNode extends ASTNode {

    public String command;

    public StepNode(SourceLocation loc, String command) {
        super(loc);
        this.command = command;
    }
}
