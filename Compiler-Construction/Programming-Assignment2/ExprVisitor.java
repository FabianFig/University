
import java.util.Map;

public class ExprVisitor extends logoBaseVisitor<Integer> {

    private final Map<String, Integer> vars;  // shared with Visitor

    public ExprVisitor(Map<String, Integer> vars) {
        this.vars = vars;
    }

    @Override
    public Integer visitAdd(logoParser.AddContext ctx) {
        return visit(ctx.e1) + visit(ctx.e2);
    }

    @Override
    public Integer visitSub(logoParser.SubContext ctx) {
        return visit(ctx.e1) - visit(ctx.e2);
    }

    @Override
    public Integer visitMul(logoParser.MulContext ctx) {
        return visit(ctx.e1) * visit(ctx.e2);
    }

    @Override
    public Integer visitDiv(logoParser.DivContext ctx) {
        return visit(ctx.e1) / visit(ctx.e2);
    }

    @Override
    public Integer visitMod(logoParser.ModContext ctx) {
        return visit(ctx.e1) % visit(ctx.e2);
    }

    @Override
    public Integer visitPow(logoParser.PowContext ctx) {
        return (int) Math.pow(visit(ctx.e1), visit(ctx.e2));
    }

    @Override
    public Integer visitParens(logoParser.ParensContext ctx) {
        return visit(ctx.e);
    }

    @Override
    public Integer visitUnaryPlus(logoParser.UnaryPlusContext ctx) {
        return visit(ctx.e);
    }

    @Override
    public Integer visitUnaryMinus(logoParser.UnaryMinusContext ctx) {
        return -visit(ctx.e);
    }

    @Override
    public Integer visitId(logoParser.IdContext ctx) {
        return vars.getOrDefault(ctx.ID().getText(), 0);
    }

    @Override
    public Integer visitInt(logoParser.IntContext ctx) {
        return Integer.parseInt(ctx.INT().getText());
    }
}
