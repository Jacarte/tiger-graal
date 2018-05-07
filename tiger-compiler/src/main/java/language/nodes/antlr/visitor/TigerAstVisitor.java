package language.nodes.antlr.visitor;

import language.TigerLang;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.ProgramNode;
import language.nodes.antlr.ast.assign.SimpleAssignNode;
import language.nodes.antlr.ast.assign.SimpleAssignNodeGen;
import language.nodes.antlr.ast.block.ExpressionListNode;
import language.nodes.antlr.ast.block.LetInTigerNode;
import language.nodes.antlr.ast.block.functions.DispatchNodeGen;
import language.nodes.antlr.ast.block.functions.FuncCallNode;
import language.nodes.antlr.ast.block.functions.FuncDeclarationNode;
import language.nodes.antlr.ast.block.loop.BreakNode;
import language.nodes.antlr.ast.block.loop.ForNode;
import language.nodes.antlr.ast.block.loop.WhileNode;
import language.nodes.antlr.ast.conditional.DebugNode;
import language.nodes.antlr.ast.conditional.IfNode;
import language.nodes.antlr.ast.conditional.IfThenElseNode;
import language.nodes.antlr.ast.declaration.DeclarationNode;
import language.nodes.antlr.ast.declaration.MemberAssignNodeGen;
import language.nodes.antlr.ast.declaration.VariableDeclarationNodeGen;
import language.nodes.antlr.ast.leaf.*;
import language.nodes.antlr.ast.ops.AsterNodeGen;
import language.nodes.antlr.ast.ops.DivNodeGen;
import language.nodes.antlr.ast.ops.MinusNodeGen;
import language.nodes.antlr.ast.ops.PlusNodeGen;
import language.nodes.antlr.ast.ops.bool.*;
import language.nodes.antlr.parser.TigerBaseVisitor;
import language.nodes.antlr.parser.TigerLexer;
import language.nodes.antlr.parser.TigerParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CodePointBuffer;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class TigerAstVisitor extends TigerBaseVisitor<ExpressionNode> {

    public static ParseTree createParseTree(InputStream istream)
            throws IOException {

        long now = System.currentTimeMillis();

        byte[] byteArray = IOUtils.toByteArray(istream);
        CharStream charStream = CodePointCharStream.fromBuffer(
                CodePointBuffer.withBytes(
                        ByteBuffer.wrap(
                                byteArray)));

        // lexing
        TigerLexer lexer = new TigerLexer(charStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);

        // parsing
        TigerParser parser = new TigerParser(tokenStream);

        System.out.println((System.currentTimeMillis() - now) +  " ms parser construction");
        return parser.program();
    }

    TigerLang lang;

    public TigerAstVisitor(TigerLang lang) {
        this.lang = lang;
    }


    public static ExpressionNode read(TigerLang lang,ParseTree tree){
        return new TigerAstVisitor(lang).visit(tree);
    }

    @Override
    public ProgramNode visitProgram(TigerParser.ProgramContext ctx) {

        long now = System.currentTimeMillis();
        ProgramNode programNode = new ProgramNode(visit(ctx.evaluation()));

        System.out.println((System.currentTimeMillis() - now) +  " ms ast visitor");
        return programNode;
    }

    @Override
    public ExpressionNode visitIntegerNode(TigerParser.IntegerNodeContext ctx) {
        return IntegerNodeGen.create(ctx.INTEGER().getSymbol());
    }

    @Override
    public ExpressionNode visitStringNode(TigerParser.StringNodeContext ctx) {
        return StringNodeGen.create(ctx.STRING().getSymbol());
    }

    @Override
    public ExpressionNode visitBreakNode(TigerParser.BreakNodeContext ctx) {
        return new BreakNode();
    }

    @Override
    public ExpressionNode visitDebugNode(TigerParser.DebugNodeContext ctx) {
        return new DebugNode();
    }

    @Override
    public ExpressionNode visitIfThenNode(TigerParser.IfThenNodeContext ctx) {
        return new IfNode(visit(ctx.cond), visit(ctx.et));
    }

    @Override
    public ExpressionNode visitIfThenElseNode(TigerParser.IfThenElseNodeContext ctx) {
        return new IfThenElseNode(visit(ctx.cond), visit(ctx.et), visit(ctx.els));
    }

    @Override
    public ExpressionNode visitNilNode(TigerParser.NilNodeContext ctx) {
        return new NilNode(ctx.NIL().getSymbol());
    }

    @Override
    public ExpressionNode visitRealNode(TigerParser.RealNodeContext ctx) {
        return RealNodeGen.create(ctx.REAL().getSymbol());
    }

    @Override
    public ExpressionNode visitWhileNode(TigerParser.WhileNodeContext ctx) {
        return new WhileNode(visit(ctx.cond), visit(ctx.dob));
    }

    @Override
    public ExpressionNode visitForNode(TigerParser.ForNodeContext ctx) {
        ExpressionNode result =  new ForNode((SimpleAssignNode) visit(ctx.init), visit(ctx.final_), visit(ctx.doex));
        return result;
    }

    @Override
    public ExpressionNode visitSAssignNode(TigerParser.SAssignNodeContext ctx) {
        return SimpleAssignNodeGen.create(ctx.ID().getSymbol().getText(), visit(ctx.evaluation()));
    }

    @Override
    public ExpressionNode visitExprListNode(TigerParser.ExprListNodeContext ctx) {

        ExpressionNode[] expr = new ExpressionNode[ctx.list.evaluation().size()];

        for(int i = 0; i < expr.length; i++)
            expr[i] = visit(ctx.expr_seq().evaluation(i));

        return new ExpressionListNode(expr);

    }

    @Override
    public ExpressionNode visitLetNode(TigerParser.LetNodeContext ctx) {

        DeclarationNode[] decs = new DeclarationNode[ctx.let().declaration().size()];

        ExpressionNode[] expr = new ExpressionNode[ctx.let().expr_seq() != null? ctx.let().expr_seq().evaluation().size():0];

        for(int i = 0; i < decs.length; i++) {
            decs[i] = (DeclarationNode) visit(ctx.let().declaration(i));
        }
        for(int i = 0; i < expr.length; i++)
            expr[i] =  visit(ctx.let().expr_seq().evaluation(i));


        ExpressionNode node = new LetInTigerNode(expr, decs);


        return node;
    }

    @Override
    public ExpressionNode visitIdNode(TigerParser.IdNodeContext ctx) {

        IdNode node = IdNodeGen.create(ctx.ID().getSymbol());

        return node;
    }

    @Override
    public ExpressionNode visitVariableAssign(TigerParser.VariableAssignContext ctx) {
        ExpressionNode init = visit(ctx.evaluation());
        ExpressionNode dec = VariableDeclarationNodeGen.create(init, ctx.ID().getSymbol().getText());

        init.setParent(dec);

        return dec;
    }

    @Override
    public ExpressionNode visitFuncDefineNode(TigerParser.FuncDefineNodeContext ctx) {


        String[] args = new String[ctx.type_fields() != null? ctx.type_fields().func_arg().size(): 0];

        for(int i = 0; i < args.length; i++)
            args[i] = ctx.type_fields().func_arg(i).name.getText();


        ExpressionNode func = new FuncDeclarationNode(lang, ctx.evaluation(), args, ctx.il.getText());

        return func;
    }

    @Override
    public ExpressionNode visitUnaryNode(TigerParser.UnaryNodeContext ctx) {
        return MinusNodeGen.create(IntegerNodeGen.create(0), visit(ctx.eval));
    }

    @Override
    public ExpressionNode visitMemberCallNode(TigerParser.MemberCallNodeContext ctx) {
        return MemberAssignNodeGen.create(visit(ctx.eval), ctx.ID().getSymbol().getText());
    }

    @Override
    public ExpressionNode visitBoolOp(TigerParser.BoolOpContext ctx) {
        ExpressionNode left = visit(ctx.left);
        ExpressionNode right = visit(ctx.right);

        ExpressionNode current = null;

        switch (ctx.op.getType()){
            case TigerParser.GREATE:
                current = GreaterEqualThanNodeGen.create(left, right);
                break;
            case TigerParser.GREATT:
                current = GreaterThanNodeGen.create(left, right);
                break;
            case TigerParser.LESSE:
                current = LessEqualNodeGen.create(left,right);
                break;
            case TigerParser.LESST:
                current = LessNodeGen.create(left, right);
                break;
            case TigerParser.EQUAL:
                current = EqualNodeGen.create(left, right);
                break;
            case TigerParser.INEQUAL:
                current = InequalNodeGen.create(left, left);
                break;
        }

        left.setParent(current);
        right.setParent(current);

        return current;
    }



    @Override
    public ExpressionNode visitFUNCALL(TigerParser.FUNCALLContext ctx) {

        ExpressionNode[] params = new ExpressionNode[ctx.expr_list()!=null? ctx.expr_list().evaluation().size():0];

        for(int i =0; i < params.length; i++) {
            params[i] = visit(ctx.expr_list().evaluation(i));

        }

        return new FuncCallNode(ctx.ID().getSymbol().getText(), params, DispatchNodeGen.create());
    }

    @Override
    public ExpressionNode visitArithOp(TigerParser.ArithOpContext ctx) {
        ExpressionNode left = visit(ctx.left);
        ExpressionNode right = visit(ctx.right);

        ExpressionNode current = null;

        switch (ctx.op.getType()){
            case TigerParser.PLUS:
                current = PlusNodeGen.create(left, right);
                break;
            case TigerParser.MINUS:
                current = MinusNodeGen.create(left, right);
                break;
            case TigerParser.ASTER:
                current = AsterNodeGen.create(left,right);
                break;
            case TigerParser.SLASH:
                current = DivNodeGen.create(left, right);
                break;
        }

        left.setParent(current);
        right.setParent(current);

        return current;
    }

    @Override
    public ExpressionNode visitBitOp(TigerParser.BitOpContext ctx) {

        ExpressionNode left = visit(ctx.left);
        ExpressionNode right = visit(ctx.right);

        ExpressionNode current = null;

        switch (ctx.op.getType()){
            case TigerParser.AMPERSAN:
                current =  AmpersanNodeGen.create(left, right);
                break;
            case TigerParser.XOR:
                current= XorOpNodeGen.create(left, right);
                break;
            case TigerParser.SHIFT_LEFT:
                current = ShiftLeftNodeGen.create(left, right);
                break;
            case TigerParser.SHIFT_RIGHT:
                current = ShiftRightNodeGen.create(left, right);
                break;
            case TigerParser.PIPE:
                current = BitOrNodeGen.create(left, right);
                break;
        }

        left.setParent(current);
        right.setParent(current);

        return current;
    }
}
