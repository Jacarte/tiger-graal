package language.nodes.antlr.ast.block.functions;

import com.oracle.truffle.api.*;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.TruffleObject;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import language.TigerLang;
import language.nodes.TigerRootNode;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.declaration.DeclarationNode;
import language.nodes.antlr.visitor.TigerAstVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

import java.lang.reflect.Type;

@NodeInfo(shortName = "func call")
public class FuncDeclarationNode extends DeclarationNode {


    String name;

    private RootCallTarget callTarget;
    TigerLang lang;
    ExpressionNode body;
    String[] argName;

    Assumption callTargetStable;

    public FuncDeclarationNode(TigerLang lang, String[] argName, ExpressionNode body,  String name){
        this.name = name;
        this.body = body;

        this.lang = lang;
        this.argName = argName;

        callTarget = Truffle.getRuntime().createCallTarget(new TigerRootNode(lang, body, new FrameDescriptor(), argName));
        callTargetStable = Truffle.getRuntime().createAssumption(name);
    }


    protected void setCallTarget(RootCallTarget callTarget) {
        this.callTarget = callTarget;
        this.callTargetStable.invalidate();
        this.callTargetStable = Truffle.getRuntime().createAssumption(name);
    }

    public RootCallTarget getCallTarget(){
        return callTarget;
    }

    public Assumption getCallTargetStable() {
        return callTargetStable;
    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {


        FrameSlot slot = defineSlot(frame, name);
        frame.setObject(slot, this);
        slot.setKind(FrameSlotKind.Object);

        // define args slots

        return null;
    }
}
