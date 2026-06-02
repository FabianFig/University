
import java.io.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

// main driver for phase 1 front end
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
        System.out.println("**GitHub Actions DSL Compiler (Phase 1)**");
        System.out.println("Input: " + filename);
        System.out.println();

        // lexing
        System.out.println("**Lexical Analysi**");
        CharStream input = CharStreams.fromFileName(filename);
        gitActionsLexer lexer = new gitActionsLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        System.out.println("Tokenisation: OK");
        System.out.println();

        // parsing
        System.out.println("**Syntactic Analysis (Parsing)**");
        gitActionsParser parser = new gitActionsParser(tokens);
        parser.removeErrorListeners();  // remove default error listener
        parser.addErrorListener(new CompilerErrorListener());

        ParseTree tree = parser.programme();
        System.out.println("Parse tree construction: OK");
        System.out.println();

        // ast construction
        System.out.println("**AST**");
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
        System.out.println("**Compilation Results**");
        for (SemanticAnalyser.Finding finding : analyser.getFindings()) {
            System.out.println(finding);
        }
        System.out.println();

        if (analyser.hasErrors()) {
            System.out.println("Compilation: FAILED (errors present)");
            System.exit(1);
        } else {
            System.out.println("Compilation: SUCCESS");
        }
    }

    // custom error listener for parser errors
    static class CompilerErrorListener extends BaseErrorListener {

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                int line, int charPositionInLine, String msg, RecognitionException e) {
            System.err.println("ERROR: " + line + ":" + charPositionInLine + ": " + msg + " [SYNTAX]");
        }
    }
}
