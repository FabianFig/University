
import java.util.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

// walks the parse tree and builds the ast
public class ASTBuilderVisitor extends gitActionsBaseVisitor<Object> {

    // pull string value from str token
    private String extractStringValue(String str) {
        if (str.length() >= 2 && str.startsWith("\"") && str.endsWith("\"")) {
            String unquoted = str.substring(1, str.length() - 1);
            // unescape quotes
            unquoted = unquoted.replace("\\\"", "\"");
            return unquoted;
        }
        return str;
    }

    // build source location from a token
    private SourceLocation tokenToLocation(Token token) {
        if (token != null) {
            return new SourceLocation(token.getLine(), token.getCharPositionInLine());
        }
        return new SourceLocation(0, 0);
    }

    // programme : workflowDeclaration onClause jobDeclaration+ EOF ;
    @Override
    public WorkflowNode visitProgramme(gitActionsParser.ProgrammeContext ctx) {
        gitActionsParser.WorkflowDeclarationContext workflowCtx = ctx.workflowDeclaration();
        gitActionsParser.OnClauseContext onCtx = ctx.onClause();
        List<gitActionsParser.JobDeclarationContext> jobCtxs = ctx.jobDeclaration();

        // pull workflow name
        String workflowName = extractStringValue(workflowCtx.STR().getText());
        SourceLocation workflowLoc = tokenToLocation(workflowCtx.getStart());

        // pull events
        List<String> events = (List<String>) visit(onCtx);

        // pull jobs
        List<JobNode> jobs = new ArrayList<>();
        for (gitActionsParser.JobDeclarationContext jobCtx : jobCtxs) {
            jobs.add((JobNode) visit(jobCtx));
        }

        return new WorkflowNode(workflowLoc, workflowName, events, jobs);
    }

    // onClause : ON eventList ;
    // this rule just forwards event extraction to eventList
    @Override
    public List<String> visitOnClause(gitActionsParser.OnClauseContext ctx) {
        return (List<String>) visit(ctx.eventList());
    }

    // eventList : IDENT (COMMA IDENT)* ;
    @Override
    public List<String> visitEventList(gitActionsParser.EventListContext ctx) {
        List<String> events = new ArrayList<>();
        List<org.antlr.v4.runtime.tree.TerminalNode> identNodes = ctx.IDENT();
        for (org.antlr.v4.runtime.tree.TerminalNode node : identNodes) {
            events.add(node.getText());
        }
        return events;
    }

    // jobDeclaration : JOB STR needsClause? runsOnClause stepsClause ;
    @Override
    public JobNode visitJobDeclaration(gitActionsParser.JobDeclarationContext ctx) {
        String jobName = extractStringValue(ctx.STR().getText());
        SourceLocation jobLoc = tokenToLocation(ctx.getStart());

        // pull needs if present
        List<String> needs = null;
        if (ctx.needsClause() != null) {
            needs = (List<String>) visit(ctx.needsClause());
        }

        // pull runs_on
        String runsOn = (String) visit(ctx.runsOnClause());

        // pull steps
        List<StepNode> steps = (List<StepNode>) visit(ctx.stepsClause());

        return new JobNode(jobLoc, jobName, needs, runsOn, steps);
    }

    // needsClause : NEEDS STR (COMMA STR)* ;
    @Override
    public List<String> visitNeedsClause(gitActionsParser.NeedsClauseContext ctx) {
        List<String> needs = new ArrayList<>();
        List<org.antlr.v4.runtime.tree.TerminalNode> strNodes = ctx.STR();
        for (org.antlr.v4.runtime.tree.TerminalNode node : strNodes) {
            needs.add(extractStringValue(node.getText()));
        }
        return needs;
    }

    // runsOnClause : RUNS_ON IDENT ;
    @Override
    public String visitRunsOnClause(gitActionsParser.RunsOnClauseContext ctx) {
        return ctx.IDENT().getText();
    }

    // stepsClause : STEPS runStep+ ;
    @Override
    public List<StepNode> visitStepsClause(gitActionsParser.StepsClauseContext ctx) {
        List<StepNode> steps = new ArrayList<>();
        List<gitActionsParser.RunStepContext> runStepCtxs = ctx.runStep();
        for (gitActionsParser.RunStepContext runCtx : runStepCtxs) {
            steps.add((StepNode) visit(runCtx));
        }
        return steps;
    }

    // runStep : RUN STR ;
    @Override
    public StepNode visitRunStep(gitActionsParser.RunStepContext ctx) {
        String command = extractStringValue(ctx.STR().getText());
        SourceLocation stepLoc = tokenToLocation(ctx.getStart());
        return new StepNode(stepLoc, command);
    }
}
