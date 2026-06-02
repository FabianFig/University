// base class for all ast nodes
// tracks source location for error reporting

public abstract class ASTNode {

    protected SourceLocation loc;

    public ASTNode(SourceLocation loc) {
        this.loc = loc;
    }

    public SourceLocation getLocation() {
        return loc;
    }
}
