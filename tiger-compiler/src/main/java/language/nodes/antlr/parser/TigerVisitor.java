// Generated from Tiger.g4 by ANTLR 4.7.1

package language.nodes.antlr.parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link TigerParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface TigerVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link TigerParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(TigerParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IntegerNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerNode(TigerParser.IntegerNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IdNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdNode(TigerParser.IdNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Statement}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(TigerParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code LetNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetNode(TigerParser.LetNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExprListNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprListNode(TigerParser.ExprListNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfNode(TigerParser.IfNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code DebugNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDebugNode(TigerParser.DebugNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MemberCallNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMemberCallNode(TigerParser.MemberCallNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CallNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCallNode(TigerParser.CallNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BreakNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBreakNode(TigerParser.BreakNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RealNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRealNode(TigerParser.RealNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EvalNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvalNode(TigerParser.EvalNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArithOp}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithOp(TigerParser.ArithOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StringNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringNode(TigerParser.StringNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IndexNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexNode(TigerParser.IndexNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NilNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNilNode(TigerParser.NilNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BoolOp}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolOp(TigerParser.BoolOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code UnaryNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryNode(TigerParser.UnaryNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BitOp}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBitOp(TigerParser.BitOpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FUNCALL}
	 * labeled alternative in {@link TigerParser#function_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFUNCALL(TigerParser.FUNCALLContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#expr_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_list(TigerParser.Expr_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#expr_seq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr_seq(TigerParser.Expr_seqContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfThenNode}
	 * labeled alternative in {@link TigerParser#if_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThenNode(TigerParser.IfThenNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IfThenElseNode}
	 * labeled alternative in {@link TigerParser#if_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThenElseNode(TigerParser.IfThenElseNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ForNode}
	 * labeled alternative in {@link TigerParser#for_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForNode(TigerParser.ForNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code SAssignNode}
	 * labeled alternative in {@link TigerParser#simple_assign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSAssignNode(TigerParser.SAssignNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WhileNode}
	 * labeled alternative in {@link TigerParser#while_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileNode(TigerParser.WhileNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FuncDefineNode}
	 * labeled alternative in {@link TigerParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDefineNode(TigerParser.FuncDefineNodeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VariableAssign}
	 * labeled alternative in {@link TigerParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableAssign(TigerParser.VariableAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#func_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_arg(TigerParser.Func_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#type_fields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_fields(TigerParser.Type_fieldsContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#variable_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable_type(TigerParser.Variable_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link TigerParser#let}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet(TigerParser.LetContext ctx);
}