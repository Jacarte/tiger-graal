package language.nodes.antlr.visitor;

import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import javafx.util.Pair;
import language.TigerLang;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.ProgramNode;
import language.nodes.antlr.ast.assign.SimpleAssignNode;
import language.nodes.antlr.ast.assign.SimpleAssignNodeGen;
import language.nodes.antlr.ast.block.ExpressionListNode;
import language.nodes.antlr.ast.block.LetInTigerNode;
import language.nodes.antlr.ast.block.functions.*;
import language.nodes.antlr.ast.block.functions.builtin.NanoTimeNode;
import language.nodes.antlr.ast.block.functions.builtin.NanoTimeNodeGen;
import language.nodes.antlr.ast.block.functions.builtin.PrintNode;
import language.nodes.antlr.ast.block.functions.builtin.WaitNode;
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
import language.services.IFunctionArgLookupProvider;
import language.services.ILookupProvider;
import language.services.providers.FunctionScopeLookupProvider;
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


    ILookupProvider provider;
    IFunctionArgLookupProvider functionDecs;

    public TigerAstVisitor(TigerLang lang, ILookupProvider provider) {
        this.lang = lang;
        this.provider = provider;
        this.functionDecs = new FunctionScopeLookupProvider(null);
    }


    public static ExpressionNode read(TigerLang lang,ParseTree tree, ILookupProvider provider){
        return new TigerAstVisitor(lang, provider).visit(tree);
    }

    @Override
    public ProgramNode visitProgram(TigerParser.ProgramContext ctx) {


        FrameSlot slot = provider.defineSlot("print");
        slot.setKind(FrameSlotKind.Object);
        provider.getFrame().setObject(slot, new FuncDeclarationNode(lang, new String[1], new PrintNode(), "println"));

        slot = provider.defineSlot("nano_time");
        slot.setKind(FrameSlotKind.Object);
        provider.getFrame().setObject(slot, new FuncDeclarationNode(lang, new String[1], NanoTimeNodeGen.create(), "nano_time"));

        slot = provider.defineSlot("wait");
        slot.setKind(FrameSlotKind.Object);
        provider.getFrame().setObject(slot, new FuncDeclarationNode(lang, new String[1], new WaitNode(), "wait"));

        ProgramNode programNode = new ProgramNode(visit(ctx.evaluation()));
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
    public ExpressionNode visitBreakNode(TigerParser.BreakNodeContext ctx)
    {
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

        //System.out.println(getIden() + ctx.REAL().getSymbol().getText());
        return RealNodeGen.create(ctx.REAL().getSymbol());
    }

    @Override
    public ExpressionNode visitWhileNode(TigerParser.WhileNodeContext ctx) {
        return new WhileNode(visit(ctx.cond), visit(ctx.dob));
    }

    @Override
    public ExpressionNode visitForNode(TigerParser.ForNodeContext ctx) {

        provider = provider.createChild(provider);

        SimpleAssignNode assign = (SimpleAssignNode) visit(ctx.init);


        ExpressionNode result =  new ForNode(
                assign.getVarId(),
                        assign.getExpr(), visit(ctx.final_), visit(ctx.doex),
                 provider.getThisSlot(assign.getVarId()), provider.getFrame());

        provider = provider.getParent();

        return result;
    }

    @Override
    public ExpressionNode visitSAssignNode(TigerParser.SAssignNodeContext ctx) {

        FrameSlot slot = provider.defineSlot(ctx.ID().getSymbol().getText());
        slot.setKind(FrameSlotKind.Long);

        return SimpleAssignNodeGen.create(ctx.ID().getSymbol().getText(), visit(ctx.evaluation()));
    }

    @Override
    public ExpressionNode visitExprListNode(TigerParser.ExprListNodeContext ctx) {


        //System.out.println(getIden() + "expression" );
        ExpressionNode[] expr = new ExpressionNode[ctx.list.evaluation().size()];

        for(int i = 0; i < expr.length; i++)
            expr[i] = visit(ctx.expr_seq().evaluation(i));

        return new ExpressionListNode(expr);

    }

    @Override
    public ExpressionNode visitLetNode(TigerParser.LetNodeContext ctx) {


        provider = provider.createChild(provider);

        DeclarationNode[] decs = new DeclarationNode[ctx.let().declaration().size()];

        ExpressionNode[] expr = new ExpressionNode[ctx.let().expr_seq() != null? ctx.let().expr_seq().evaluation().size():0];

        for(int i = 0; i < decs.length; i++) {
            decs[i] = (DeclarationNode) visit(ctx.let().declaration(i));
        }
        for(int i = 0; i < expr.length; i++)
            expr[i] =  visit(ctx.let().expr_seq().evaluation(i));


        ExpressionNode node = new LetInTigerNode(expr, decs);

        provider = provider.getParent();

        return node;
    }

    @Override
    public ExpressionNode visitIdNode(TigerParser.IdNodeContext ctx) {

        Pair<ArgDeclaration, Integer> arg = functionDecs.getArg(ctx.ID().getSymbol().getText());

        if(arg != null)
            return ArgNodeGen.create(arg.getKey().getIndex(), arg.getValue());
        else {

            Pair<VirtualFrame, FrameSlot> pair = provider.findUp(ctx.ID().getSymbol().getText());
            return IdNodeGen.create(ctx.ID().getSymbol(), pair.getKey(), pair.getValue());
        }
    }

    @Override
    public ExpressionNode visitVariableAssign(TigerParser.VariableAssignContext ctx) {



        FrameSlot slot = provider.defineSlot(ctx.ID().getSymbol().getText());
        VirtualFrame fr = provider.getFrame();

        ExpressionNode init = visit(ctx.evaluation());
        ExpressionNode dec = VariableDeclarationNodeGen.create(init, ctx.ID().getSymbol().getText(), slot, fr);

        init.setParent(dec);

        return dec;
    }


    @Override
    public ExpressionNode visitFuncDefineNode(TigerParser.FuncDefineNodeContext ctx) {


        String[] args = new String[ctx.type_fields() != null? ctx.type_fields().func_arg().size(): 0];
        FrameSlot slot = provider.defineSlot(ctx.il.getText());
        slot.setKind(FrameSlotKind.Object);

        functionDecs = functionDecs.addChild(functionDecs);

        for(int i = 0; i < args.length; i++) {
            args[i] = ctx.type_fields().func_arg(i).name.getText();
            functionDecs.addArgNode(args[i], new ArgDeclaration(i));
        }

        provider = provider.createChild(provider);

        ExpressionNode func = new FuncDeclarationNode(lang,args, visit(ctx.evaluation()), ctx.il.getText());

        provider = provider.getParent();

        provider.getFrame().setObject(slot, func);

        functionDecs = functionDecs.getParent();

        return func;
    }

    @Override
    public ExpressionNode visitUnaryNode(TigerParser.UnaryNodeContext ctx) {
        return MinusNodeGen.create(IntegerNodeGen.create(0), visit(ctx.eval));
    }

    @Override
    public ExpressionNode visitMemberCallNode(TigerParser.MemberCallNodeContext ctx) {

        Pair<VirtualFrame, FrameSlot> pair = provider.findUp(ctx.ID().getSymbol().getText());

        return MemberAssignNodeGen.create(visit(ctx.eval), ctx.ID().getSymbol().getText(), pair.getValue(), pair.getKey());
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


        // System.out.println(getIden() + "funcall " + ctx.ID().getSymbol().getText());
        ExpressionNode[] params = new ExpressionNode[ctx.expr_list()!=null? ctx.expr_list().evaluation().size():0];

        for(int i =0; i < params.length; i++) {
            params[i] = visit(ctx.expr_list().evaluation(i));

        }

        Pair<VirtualFrame, FrameSlot> pair = provider.findUp(ctx.ID().getSymbol().getText());

        return new FuncCallNode(ctx.ID().getSymbol().getText(), params, pair.getValue(), pair.getKey());
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
