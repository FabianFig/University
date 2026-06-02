import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.List;
import java.nio.file.Files;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        CharStream input = CharStreams.fromReader(new InputStreamReader(System.in));

        PA4Lexer lexer = new PA4Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PA4Parser parser = new PA4Parser(tokens);

        ParseTree tree = parser.program();

        AstBuilder astBuilder = new AstBuilder();
        Program program = (Program) astBuilder.visit(tree);

        Set<String> definedFunctions = program.definitions.stream()
                .map(d -> d.name)
                .collect(Collectors.toSet());

        if (!definedFunctions.contains("main")) {
            System.err.println("Compile-time error: 'main' function not defined.");
            System.exit(1);
        }

        Set<String> calledFunctions = new HashSet<>();
        program.getCalls(calledFunctions);

        for (String call : calledFunctions) {
            if (!definedFunctions.contains(call)) {
                System.err.println("Compile-time error: function '" + call + "' is called but not defined.");
                System.exit(1);
            }
        }

        IlocGen gen = new IlocGen() ;
        List<String> code = gen.generate(program);

        Files.copy(Path.of("./template.iloc.txt"), System.out);
        for (String line : code) {
            System.out.println(line);
        }
    }
}