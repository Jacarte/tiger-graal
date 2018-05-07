package language.nodes.antlr.ast.leaf;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.*;
import language.NilValue;
import language.nodes.antlr.ast.block.functions.FuncDefinitionNode;
import org.antlr.v4.runtime.Token;

import java.lang.reflect.Type;

public abstract class IdNode extends TerminalNode {

    public Type getType() {
        return Object.class;
    }


    public static interface FrameGet<T> {
        T get(Frame frame, FrameSlot slot) throws FrameSlotTypeException;
    }


    public static interface FrameSet<T> {
        void set(Frame frame, FrameSlot slot, T value) throws FrameSlotTypeException;
    }


    @Specialization(rewriteOn = FrameSlotTypeException.class,
    guards = {"isLong(virtualFrame)"})
    public long readLong(VirtualFrame virtualFrame)
            throws FrameSlotTypeException {

        return readUpStack(Frame::getLong, virtualFrame, getName());
    }

    @Specialization(rewriteOn = FrameSlotTypeException.class,
        guards = {"isDouble(virtualFrame)"}
    )
    public double readDouble(VirtualFrame virtualFrame)
            throws FrameSlotTypeException {

        return readUpStack(Frame::getDouble, virtualFrame, getName());
    }


    boolean isDouble(VirtualFrame frame){
        return getSlotUp(frame, getName()).getKind() == FrameSlotKind.Double;
    }

    boolean isLong(VirtualFrame frame){
        return getSlotUp(frame, getName()).getKind() == FrameSlotKind.Long;
    }



    @Specialization(rewriteOn = FrameSlotTypeException.class)
    public FuncDefinitionNode readFunction(VirtualFrame virtualFrame)
            throws FrameSlotTypeException {


        return (FuncDefinitionNode) readUpStack(Frame::getObject, virtualFrame, getName());
    }


    public IdNode(Token payload) {
        super(payload);
    }

    public String getName() {
        return super.token.getText();
    }

    @Override
    public String toString() {
        return getName();


    }
}

