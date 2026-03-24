// Generated from logo.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link logoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface logoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code fd}
	 * labeled alternative in {@link logoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFd(logoParser.FdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bk}
	 * labeled alternative in {@link logoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBk(logoParser.BkContext ctx);
	/**
	 * Visit a parse tree produced by the {@code rt}
	 * labeled alternative in {@link logoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRt(logoParser.RtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code lt}
	 * labeled alternative in {@link logoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLt(logoParser.LtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pu}
	 * labeled alternative in {@link logoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPu(logoParser.PuContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pd}
	 * labeled alternative in {@link logoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPd(logoParser.PdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code hm}
	 * labeled alternative in {@link logoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHm(logoParser.HmContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sc}
	 * labeled alternative in {@link logoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSc(logoParser.ScContext ctx);
	/**
	 * Visit a parse tree produced by the {@code repeat}
	 * labeled alternative in {@link logoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeat(logoParser.RepeatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ifz}
	 * labeled alternative in {@link logoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfz(logoParser.IfzContext ctx);
	/**
	 * Visit a parse tree produced by the {@code as}
	 * labeled alternative in {@link logoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAs(logoParser.AsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code wd}
	 * labeled alternative in {@link logoParser#stmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWd(logoParser.WdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code div}
	 * labeled alternative in {@link logoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(logoParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code add}
	 * labeled alternative in {@link logoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(logoParser.AddContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sub}
	 * labeled alternative in {@link logoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub(logoParser.SubContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parens}
	 * labeled alternative in {@link logoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(logoParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mod}
	 * labeled alternative in {@link logoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMod(logoParser.ModContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryPlus}
	 * labeled alternative in {@link logoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryPlus(logoParser.UnaryPlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code mul}
	 * labeled alternative in {@link logoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(logoParser.MulContext ctx);
	/**
	 * Visit a parse tree produced by the {@code unaryMinus}
	 * labeled alternative in {@link logoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryMinus(logoParser.UnaryMinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pow}
	 * labeled alternative in {@link logoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPow(logoParser.PowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code id}
	 * labeled alternative in {@link logoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(logoParser.IdContext ctx);
	/**
	 * Visit a parse tree produced by the {@code int}
	 * labeled alternative in {@link logoParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(logoParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by {@link logoParser#stmts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStmts(logoParser.StmtsContext ctx);
}