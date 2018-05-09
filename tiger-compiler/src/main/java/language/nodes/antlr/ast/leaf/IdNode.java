package language.nodes.antlr.ast.leaf;

import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.*;
import language.NilValue;
import language.nodes.antlr.ast.block.functions.FuncDefinitionNode;
import org.antlr.v4.runtime.Token;

import java.lang.reflect.Type;

@NodeField(name = "scope", type = VirtualFrame.class)
@NodeField(name = "slot", type = FrameSlot.class)
public abstract class IdNode extends TerminalNode {

    public abstract FrameSlot getSlot();
    public abstract VirtualFrame getScope();

    public Type getType() {
        return Object.class;
    }


    public interface FrameGet<T> {
        T get(Frame frame, FrameSlot slot) throws FrameSlotTypeException;
    }


    public interface FrameSet<T> {
        void set(Frame frame, FrameSlot slot, T value) throws FrameSlotTypeException;
    }


    @Specialization(rewriteOn = FrameSlotTypeException.class,
    guards = {"isLong(virtualFrame)"})
    public long readLong(VirtualFrame virtualFrame)
            throws FrameSlotTypeException {

        return getScope().getLong(getSlot());
    }

    @Specialization(rewriteOn = FrameSlotTypeException.class,
        guards = {"isDouble(virtualFrame)"}
    )
    public double readDouble(VirtualFrame virtualFrame)
            throws FrameSlotTypeException {

        return getScope().getDouble(getSlot());
    }


    boolean isDouble(VirtualFrame frame){
        return getSlot().getKind() == FrameSlotKind.Double;
    }

    boolean isLong(VirtualFrame frame){

        return getSlot().getKind() == FrameSlotKind.Long;
    }



    @Specialization(rewriteOn = FrameSlotTypeException.class)
    public FuncDefinitionNode readFunction(VirtualFrame virtualFrame)
            throws FrameSlotTypeException {


        return (FuncDefinitionNode) getScope().getObject(getSlot());
    }



    @Specialization(rewriteOn = FrameSlotTypeException.class)
    public Object readObj(VirtualFrame virtualFrame)
            throws FrameSlotTypeException {

        return  getScope().getObject(getSlot());
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

