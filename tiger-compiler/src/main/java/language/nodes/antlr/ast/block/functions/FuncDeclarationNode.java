package language.nodes.antlr.ast.block.functions;

import com.oracle.truffle.api.*;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.nodes.Node;
import language.TigerLang;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.visitor.TigerAstVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.reflect.Type;

public class FuncDeclarationNode {

    ParseTree blockNode;

    RootCallTarget exprNode;

    String name;

    public TigerLang lang;
private Assumption callTargetStable;

    public FuncDeclarationNode(TigerLang lang, ParseTree blockNode, String[] argName, String name){
        this.blockNode = blockNode;
        this.name = name;

        this.lang = lang;

        exprNode = new TigerAstVisitor(lang).visit(blockNode);
        callTargetStable = Truffle.getRuntime().createAssumption(name);
    }


    public ExpressionNode getBlock(){
        return exprNode;
    }

    @Override
    public long getNumberOfArguments() {
        return args.length;
    }

    @Override
    public String toString() {
        return "func_def: " + name;
    }


    @Override
    public Type getType() {
        return FuncDefinitionNode.class;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {

        CompilerAsserts.compilationConstant(name);
        CompilerAsserts.compilationConstant(blockNode);


        FrameSlot slot = defineSlot(frame, name);
        frame.setObject(slot, this);
        slot.setKind(FrameSlotKind.Object);

        return this;
    }

    CallTarget target;

    public CallTarget getCallTarget(){
        return target;
    }

    public void setCallTarget(CallTarget target){
        this.target = target;
        this.callTargetStable.invalidate();
        this.callTargetStable = Truffle.getRuntime().createAssumption(name);
    }

    public Assumption getCallTargetStable() {
        return callTargetStable;
    }
}
