// tracks line and column for error reporting

public class SourceLocation {

    public int line;
    public int column;

    public SourceLocation(int line, int column) {
        this.line = line;
        this.column = column;
    }

    @Override
    public String toString() {
        return line + ":" + column;
    }
}
