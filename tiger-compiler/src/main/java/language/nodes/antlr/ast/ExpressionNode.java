package language.nodes.antlr.ast;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.*;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import language.NilValue;
import language.TigerTypesGen;
import language.nodes.antlr.ast.block.functions.FuncDefinitionNode;
import language.nodes.antlr.ast.leaf.IdNode;
import language.nodes.antlr.ast.scopes.ScopeException;

import java.lang.reflect.Type;

public abstract class ExpressionNode extends BaseTigerNode{



    public abstract Type getType();

    public abstract Object executeGeneric(VirtualFrame frame);

    public long executeLong(VirtualFrame virtualFrame)
            throws UnexpectedResultException {
        return TigerTypesGen.expectLong(
                this.executeGeneric(virtualFrame));
    }


    public double executeDouble(VirtualFrame virtualFrame)
            throws UnexpectedResultException {
        return TigerTypesGen.expectDouble(
                this.executeGeneric(virtualFrame));
    }


    public VirtualFrame createChild(VirtualFrame parent){
        return Truffle.getRuntime().createVirtualFrame(new Object[]{parent}, new FrameDescriptor());
    }


    public FrameSlot getSlot(Frame fr, String name){
        return fr.getFrameDescriptor().findFrameSlot(name);
    }

    public FrameSlot getSlotUp(Frame fr, String name){

        FrameSlot slot = fr.getFrameDescriptor().findFrameSlot(name);

        while(slot == null){
            fr = getParentFrame(fr);

            if(fr == null)
                throw  new ScopeException("The variable is not defined " + name);

            slot = fr.getFrameDescriptor().findFrameSlot(name);
        }

        return slot;
    }


    public FrameSlot defineSlot(Frame fr, String name){
        return fr.getFrameDescriptor().addFrameSlot(name);
    }

    public <T> void writeUpStack(IdNode.FrameSet<T> setter, Frame frame, String name, T value, FrameSlotKind kind){
        FrameSlot slot = getSlot(frame, name);

        while(slot == null){
            frame = this.getParentFrame(frame);

            if(frame == null){
                throw new RuntimeException("Unknown variable: " + name);
            }
            slot = getSlot(frame, name);

        }

        try {
            setter.set(frame, slot, value);
            slot.setKind(kind);
        } catch (FrameSlotTypeException e) {
            e.printStackTrace();
        }
    }

    public <T> T readUpStack(IdNode.FrameGet<T> getter, Frame frame, String name)
            throws FrameSlotTypeException {
        FrameSlot slot = getSlot(frame, name);

        T value = slot == null? null : getter.get(frame, slot);
        while (value == null) {

            frame = this.getParentFrame(frame);
            if (frame == null) {
                throw new RuntimeException("Unknown variable: " + name);
            }
            slot = getSlot(frame, name);
            value = slot == null ? null : getter.get(frame, slot);
        }
        return value;
    }


    Frame getParentFrame(Frame f){
        try{ return (Frame)f.getArguments()[0];}
        catch (Exception e){return null;}
    }

}
