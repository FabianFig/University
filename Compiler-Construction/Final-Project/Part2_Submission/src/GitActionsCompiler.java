
import java.io.*;
import java.nio.file.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

// main driver — phase 2: full compilation including yaml emission
public class GitActionsCompiler {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java GitActionsCompiler <input.gha>");
            System.exit(1);
        }

        String inputFile = args[0];
        try {
            compile(inputFile);
        } catch (Exception e) {
            System.err.println("FATAL: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void compile(String filename) throws IOException {
        System.out.println("**GitHub Actions DSL Compiler**");
        System.out.println("Input: " + filename);
        System.out.println();

        // lexical analysis
        System.out.println("**Lexical Analysis**");
        CharStream input = CharStreams.fromFileName(filename);
        gitActionsLexer lexer = new gitActionsLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        System.out.println("Tokenisation: OK");
        System.out.println();

        // parsing
        System.out.println("**Syntactic Analysis (Parsing)**");
        gitActionsParser parser = new gitActionsParser(tokens);
        parser.removeErrorListeners();

        CompilerErrorListener errorListener = new CompilerErrorListener();
        parser.addErrorListener(errorListener);

        ParseTree tree = parser.programme();
        System.out.println("Parse tree construction: OK");
        System.out.println();

        // exit cleanly if syntax errors were found
        if (errorListener.hasError()) {
            System.out.println("Compilation: FAILED (syntax errors — see above)");
            System.exit(1);
        }

        // ast construction
        System.out.println("**AST Construction**");
        ASTBuilderVisitor builder = new ASTBuilderVisitor();
        WorkflowNode workflow = (WorkflowNode) builder.visit(tree);
        System.out.println("AST construction: OK");
        System.out.println("Workflow name: " + workflow.name);
        System.out.println("Events: " + workflow.events);
        System.out.println("Jobs: " + workflow.jobs.size());
        System.out.println();

        // semantic checks
        System.out.println("**Semantic Checks**");
        SemanticAnalyser analyser = new SemanticAnalyser();
        analyser.analyze(workflow);
        System.out.println("Semantic analysis: OK");
        System.out.println();

        // findings report
        System.out.println("**Findings**");
        if (analyser.getFindings().isEmpty()) {
            System.out.println("No issues found.");
        } else {
            for (SemanticAnalyser.Finding finding : analyser.getFindings()) {
                System.out.println(finding);
            }
        }
        System.out.println();

        if (analyser.hasErrors()) {
            System.out.println("Compilation: FAILED (errors present — no YAML generated)");
            System.exit(1);
        }

        // yaml emission — only reached if no errors
        System.out.println("**YAML Code Generation**");
        YAMLEmitter emitter = new YAMLEmitter();
        String yaml = emitter.emit(workflow);

        // derive output filename: replace .gha with .yml
        String outputFile = filename.replaceAll("\\.gha$", ".yml");
        Files.writeString(Path.of(outputFile), yaml);

        System.out.println("Output: " + outputFile);
        System.out.println();
        System.out.println(yaml);
        System.out.println("Compilation: SUCCESS");
    }

    // custom error listener — includes line and column in output
    static class CompilerErrorListener extends BaseErrorListener {

        private boolean hasError = false;

        public boolean hasError() {
            return hasError;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                int line, int charPositionInLine, String msg, RecognitionException e) {
            hasError = true;
            System.err.println("ERROR: " + line + ":" + charPositionInLine + ": " + msg + " [SYNTAX]");
        }
    }
}
