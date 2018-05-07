// Generated from Tiger.g4 by ANTLR 4.7.1

package language.nodes.antlr.parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TigerParser}.
 */
public interface TigerListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TigerParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(TigerParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(TigerParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IntegerNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterIntegerNode(TigerParser.IntegerNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IntegerNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitIntegerNode(TigerParser.IntegerNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IdNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterIdNode(TigerParser.IdNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IdNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitIdNode(TigerParser.IdNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Statement}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterStatement(TigerParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Statement}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitStatement(TigerParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code LetNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterLetNode(TigerParser.LetNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code LetNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitLetNode(TigerParser.LetNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExprListNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterExprListNode(TigerParser.ExprListNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExprListNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitExprListNode(TigerParser.ExprListNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterIfNode(TigerParser.IfNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitIfNode(TigerParser.IfNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code DebugNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterDebugNode(TigerParser.DebugNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code DebugNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitDebugNode(TigerParser.DebugNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MemberCallNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterMemberCallNode(TigerParser.MemberCallNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MemberCallNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitMemberCallNode(TigerParser.MemberCallNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CallNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterCallNode(TigerParser.CallNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CallNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitCallNode(TigerParser.CallNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BreakNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterBreakNode(TigerParser.BreakNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BreakNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitBreakNode(TigerParser.BreakNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RealNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterRealNode(TigerParser.RealNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RealNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitRealNode(TigerParser.RealNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EvalNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterEvalNode(TigerParser.EvalNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EvalNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitEvalNode(TigerParser.EvalNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithOp}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterArithOp(TigerParser.ArithOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithOp}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitArithOp(TigerParser.ArithOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StringNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterStringNode(TigerParser.StringNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StringNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitStringNode(TigerParser.StringNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IndexNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterIndexNode(TigerParser.IndexNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IndexNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitIndexNode(TigerParser.IndexNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NilNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterNilNode(TigerParser.NilNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NilNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitNilNode(TigerParser.NilNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BoolOp}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterBoolOp(TigerParser.BoolOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BoolOp}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitBoolOp(TigerParser.BoolOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code UnaryNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterUnaryNode(TigerParser.UnaryNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code UnaryNode}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitUnaryNode(TigerParser.UnaryNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BitOp}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void enterBitOp(TigerParser.BitOpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BitOp}
	 * labeled alternative in {@link TigerParser#evaluation}.
	 * @param ctx the parse tree
	 */
	void exitBitOp(TigerParser.BitOpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FUNCALL}
	 * labeled alternative in {@link TigerParser#function_call}.
	 * @param ctx the parse tree
	 */
	void enterFUNCALL(TigerParser.FUNCALLContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FUNCALL}
	 * labeled alternative in {@link TigerParser#function_call}.
	 * @param ctx the parse tree
	 */
	void exitFUNCALL(TigerParser.FUNCALLContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void enterExpr_list(TigerParser.Expr_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#expr_list}.
	 * @param ctx the parse tree
	 */
	void exitExpr_list(TigerParser.Expr_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#expr_seq}.
	 * @param ctx the parse tree
	 */
	void enterExpr_seq(TigerParser.Expr_seqContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#expr_seq}.
	 * @param ctx the parse tree
	 */
	void exitExpr_seq(TigerParser.Expr_seqContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfThenNode}
	 * labeled alternative in {@link TigerParser#if_}.
	 * @param ctx the parse tree
	 */
	void enterIfThenNode(TigerParser.IfThenNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfThenNode}
	 * labeled alternative in {@link TigerParser#if_}.
	 * @param ctx the parse tree
	 */
	void exitIfThenNode(TigerParser.IfThenNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IfThenElseNode}
	 * labeled alternative in {@link TigerParser#if_}.
	 * @param ctx the parse tree
	 */
	void enterIfThenElseNode(TigerParser.IfThenElseNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IfThenElseNode}
	 * labeled alternative in {@link TigerParser#if_}.
	 * @param ctx the parse tree
	 */
	void exitIfThenElseNode(TigerParser.IfThenElseNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ForNode}
	 * labeled alternative in {@link TigerParser#for_}.
	 * @param ctx the parse tree
	 */
	void enterForNode(TigerParser.ForNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ForNode}
	 * labeled alternative in {@link TigerParser#for_}.
	 * @param ctx the parse tree
	 */
	void exitForNode(TigerParser.ForNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code SAssignNode}
	 * labeled alternative in {@link TigerParser#simple_assign}.
	 * @param ctx the parse tree
	 */
	void enterSAssignNode(TigerParser.SAssignNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code SAssignNode}
	 * labeled alternative in {@link TigerParser#simple_assign}.
	 * @param ctx the parse tree
	 */
	void exitSAssignNode(TigerParser.SAssignNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WhileNode}
	 * labeled alternative in {@link TigerParser#while_}.
	 * @param ctx the parse tree
	 */
	void enterWhileNode(TigerParser.WhileNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WhileNode}
	 * labeled alternative in {@link TigerParser#while_}.
	 * @param ctx the parse tree
	 */
	void exitWhileNode(TigerParser.WhileNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FuncDefineNode}
	 * labeled alternative in {@link TigerParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterFuncDefineNode(TigerParser.FuncDefineNodeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FuncDefineNode}
	 * labeled alternative in {@link TigerParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitFuncDefineNode(TigerParser.FuncDefineNodeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VariableAssign}
	 * labeled alternative in {@link TigerParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssign(TigerParser.VariableAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VariableAssign}
	 * labeled alternative in {@link TigerParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssign(TigerParser.VariableAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#func_arg}.
	 * @param ctx the parse tree
	 */
	void enterFunc_arg(TigerParser.Func_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#func_arg}.
	 * @param ctx the parse tree
	 */
	void exitFunc_arg(TigerParser.Func_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#type_fields}.
	 * @param ctx the parse tree
	 */
	void enterType_fields(TigerParser.Type_fieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#type_fields}.
	 * @param ctx the parse tree
	 */
	void exitType_fields(TigerParser.Type_fieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#variable_type}.
	 * @param ctx the parse tree
	 */
	void enterVariable_type(TigerParser.Variable_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#variable_type}.
	 * @param ctx the parse tree
	 */
	void exitVariable_type(TigerParser.Variable_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TigerParser#let}.
	 * @param ctx the parse tree
	 */
	void enterLet(TigerParser.LetContext ctx);
	/**
	 * Exit a parse tree produced by {@link TigerParser#let}.
	 * @param ctx the parse tree
	 */
	void exitLet(TigerParser.LetContext ctx);
}