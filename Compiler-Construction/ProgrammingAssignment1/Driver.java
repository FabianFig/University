
import org.antlr.v4.runtime.*;
import java.util.Stack;

public class Driver {

    public static void main(String[] args) throws Exception {
        CharStream inp = CharStreams.fromStream(System.in);
        XMLLexer lexer = new XMLLexer(inp);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();

        Stack<String> stack = new Stack<>();

        for (Token token : tokens.getTokens()) {
        }
    }
}
