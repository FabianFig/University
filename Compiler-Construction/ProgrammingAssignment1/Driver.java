
import org.antlr.v4.runtime.*;
import java.util.Stack;
import java.util.List;

public class Driver {

    public static void main(String[] args) throws Exception {
        // read input from standard input (system.in)
        CharStream input = CharStreams.fromStream(System.in);
        XMLLexer lexer = new XMLLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        tokens.fill();
        List<Token> tokenList = tokens.getTokens();

        // stack implemeentation for tracking open tags
        Stack<String> stack = new Stack<>();
        boolean isRunning = true; // flag to control main loop, set false on error or eof
        int i = 0;

        // main token processing loop
        while (i < tokenList.size() && isRunning) {
            Token t = tokenList.get(i);
            int type = t.getType();

            if (type == Token.EOF) {
                // end of input
                isRunning = false;
            } else if (type == XMLLexer.OPEN_TAG) {
                // push opening tag name onto stack
                String text = t.getText();
                stack.push(text.substring(1, text.length() - 1));
            } else if (type == XMLLexer.CLOSE_TAG) {
                // check if closing tag matches the last opened tag
                String text = t.getText();
                String name = text.substring(2, text.length() - 1);

                if (stack.isEmpty()) {
                    // no matching opening tag error
                    System.out.println("ERROR: UNMATCHED-CLOSE </" + name + ">");
                    isRunning = false;
                } else {
                    String lastOpen = stack.pop();
                    if (!lastOpen.equals(name)) {
                        // mismatched closing tag error
                        System.out.println("ERROR: UNMATCHED-CLOSE </" + name + ">. Expecting </" + lastOpen + ">.");
                        isRunning = false;
                    }
                }
            }
            // advace to next token
            i++;
        }

        // after processing: checking for any unmatched open tags if no error occured
        if (isRunning) {
            if (stack.isEmpty()) {
                // all tags matched, yay
                System.out.println("OK");
            } else {
                // print unmatched open tags (from most recent to oldest)
                while (!stack.isEmpty()) {
                    System.out.println("ERROR: UNMATCHED-OPEN <" + stack.pop() + ">");
                }
            }
        }
    }
}
