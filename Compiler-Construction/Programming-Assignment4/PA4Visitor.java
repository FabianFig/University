// Generated from PA4.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PA4Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PA4Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PA4Parser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(PA4Parser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link PA4Parser#definition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefinition(PA4Parser.DefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link PA4Parser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(PA4Parser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link PA4Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(PA4Parser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link PA4Parser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(PA4Parser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link PA4Parser#add}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdd(PA4Parser.AddContext ctx);
	/**
	 * Visit a parse tree produced by {@link PA4Parser#mul}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMul(PA4Parser.MulContext ctx);
	/**
	 * Visit a parse tree produced by {@link PA4Parser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(PA4Parser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link PA4Parser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(PA4Parser.PrimaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link PA4Parser#fncall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFncall(PA4Parser.FncallContext ctx);
	/**
	 * Visit a parse tree produced by {@link PA4Parser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(PA4Parser.ArgsContext ctx);
}