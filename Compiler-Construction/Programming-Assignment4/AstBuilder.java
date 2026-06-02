import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import org.antlr.v4.runtime.tree.TerminalNode;

class AstBuilder extends PA4BaseVisitor<AST> {

    @Override
    public AST visitExpr(PA4Parser.ExprContext ctx) {
        return visit(ctx.cond());
    }

    @Override
    public AST visitCond(PA4Parser.CondContext ctx) {
        if (ctx.getChildCount() == 1) {
            return visit(ctx.add());
        }
        return new Cond(
            (Expr) visit(ctx.add()),
            (Expr) visit(ctx.expr()),
            (Expr) visit(ctx.cond())
        );
    }

    @Override
    public AST visitAdd(PA4Parser.AddContext ctx) {
        Expr node = (Expr) visit(ctx.mul(0));
        for (int i = 1; i < ctx.mul().size(); i++) {
            String op = ctx.getChild(2*i - 1).getText();
            Expr right = (Expr) visit(ctx.mul(i));
            node = new Bin(op, node, right);
        }
        return node;
    }

    @Override
    public AST visitMul(PA4Parser.MulContext ctx) {
        Expr node = (Expr) visit(ctx.unary(0));
        for (int i = 1; i < ctx.unary().size(); i++) {
            String op = ctx.getChild(2*i - 1).getText();
            Expr right = (Expr) visit(ctx.unary(i));
            node = new Bin(op, node, right);
        }
        return node;
    }

    @Override
    public AST visitUnary(PA4Parser.UnaryContext ctx) {
        return visit(ctx.primary());
    }

    @Override
    public AST visitProgram(PA4Parser.ProgramContext ctx) {
        List<Definition> LS = ctx.definition().stream()
                  .map(this::visit)
                  .map(e -> (Definition) e)
                  .toList();
        return new Program(LS) ; 
    }

    @Override
    public AST visitDefinition(PA4Parser.DefinitionContext ctx) {
        String name = ctx.ID().getText() ; 

        List<String> params = new ArrayList<>();
        if (ctx.params() != null && ctx.params().ID() != null) {
            for (TerminalNode id : ctx.params().ID()) {
                params.add(id.getText());
            }
        }
        
        Expr body = (Expr) visit(ctx.expr());
        
        return new Definition(name, params, body) ; 
    }

    @Override
    public AST visitPrimary(PA4Parser.PrimaryContext ctx) {
        if (ctx.INT() != null) {
            return new Int(Long.parseLong(ctx.INT().getText()));
        }
        if (ctx.ID() != null) {
            return new Var(ctx.ID().getText());
        }
        if (ctx.fncall() != null) {
            return visit(ctx.fncall()) ; 
        }
        return visit(ctx.expr());
    }

    @Override
    public AST visitFncall(PA4Parser.FncallContext ctx) {
        String name = ctx.ID().getText() ; 
        
        List<Expr> args = new ArrayList<>();
        if (ctx.args() != null && ctx.args().expr() != null) {
            for (PA4Parser.ExprContext exprCtx : ctx.args().expr()) {
                args.add((Expr) visit(exprCtx));
            }
        }
        
        return new FnCall(name, args) ; 
    }
    
}
