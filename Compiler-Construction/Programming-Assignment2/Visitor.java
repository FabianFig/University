
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Visitor extends logoBaseVisitor<Void> {

    private final Engine engine;
    private final Map<String, Integer> vars = new HashMap<>();
    private final ExprVisitor exprVisitor = new ExprVisitor(vars);

    public Visitor(Engine _engine) {
        engine = _engine;
    }

    private int val(logoParser.ExprContext e) {
        return exprVisitor.visit(e);
    }

    @Override
    public Void visitRepeat(logoParser.RepeatContext ctx) {
        int k = val(ctx.expr());
        for (int i = 0; i < k; i++) {
            for (var s : ctx.stmts().stmt()) {
                visit(s);
            }
        }
        return null;
    }

    @Override
    public Void visitFd(logoParser.FdContext ctx) {
        engine.move(val(ctx.expr()));
        return null;
    }

    @Override
    public Void visitBk(logoParser.BkContext ctx) {
        engine.move(-val(ctx.expr()));
        return null;
    }

    @Override
    public Void visitRt(logoParser.RtContext ctx) {
        engine.rotate(val(ctx.expr()));
        return null;
    }

    @Override
    public Void visitLt(logoParser.LtContext ctx) {
        engine.rotate(-val(ctx.expr()));
        return null;
    }

    @Override
    public Void visitPu(logoParser.PuContext ctx) {
        engine.penUp();
        return null;
    }

    @Override
    public Void visitPd(logoParser.PdContext ctx) {
        engine.penDown();
        return null;
    }

    @Override
    public Void visitHm(logoParser.HmContext ctx) {
        engine.home();
        return null;
    }

    @Override
    public Void visitSc(logoParser.ScContext ctx) {
        engine.setColor(val(ctx.expr()));
        return null;
    }

    @Override
    public Void visitAs(logoParser.AsContext ctx) {
        vars.put(ctx.ID().getText(), val(ctx.expr()));
        return null;
    }

    @Override
    public Void visitIfz(logoParser.IfzContext ctx) {
        List<logoParser.StmtsContext> branches = ctx.stmts();
        logoParser.StmtsContext branch = val(ctx.expr()) == 0 ? branches.get(0) : branches.get(1);
        for (var s : branch.stmt()) {
            visit(s);
        }
        return null;
    }

    @Override
    public Void visitWd(logoParser.WdContext ctx) {
        engine.setPenWidth(val(ctx.expr()));
        return null;
    }
}
