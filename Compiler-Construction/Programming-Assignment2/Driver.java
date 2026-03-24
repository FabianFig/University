
import java.io.IOException;

import org.antlr.v4.runtime.*;

public class Driver {

    public static void main(String[] args) throws IOException {
        final int SIZE = args.length == 0 ? 700 : Integer.parseInt(args[0]);
        CharStream input = CharStreams.fromStream(System.in);
        logoLexer lexer = new logoLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        logoParser parser = new logoParser(tokens);

        Engine logoEngine = new SVGEngine(SIZE, SIZE);
        Visitor visitor = new Visitor(logoEngine);

        logoEngine.open();

        while (parser.getCurrentToken().getType() != Token.EOF) {
            visitor.visit(parser.stmt());
        }
        logoEngine.close();
    }
}
