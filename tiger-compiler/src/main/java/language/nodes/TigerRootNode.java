package language.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.*;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.RootNode;
import language.nodes.antlr.ast.ExpressionNode;
import sun.jvm.hotspot.debugger.Address;

import java.util.Map;

public class TigerRootNode extends RootNode {

    @Child private ExpressionNode mainNode;
    @CompilerDirectives.CompilationFinal private String name;

    String[] args;

    public TigerRootNode(TruffleLanguage<?> language, ExpressionNode node, FrameDescriptor descriptor, String[] args) {
        super(language, descriptor);
        this.mainNode = node;
        this.args = args;
    }


    void setByType(String name, Object value, Frame fr){

        FrameSlot slot = fr.getFrameDescriptor().findOrAddFrameSlot(name);

        if(value instanceof Long){
            slot.setKind(FrameSlotKind.Long);
            fr.setLong(slot, (long)value);
        }
        else if(value instanceof Double) {
            slot.setKind(FrameSlotKind.Double);
            fr.setDouble(slot, (double) value);
        }
        else {
            slot.setKind(FrameSlotKind.Object);
            fr.setObject(slot, value);
        }
    }

    @Override
    @ExplodeLoop
    public Object execute(VirtualFrame frame) {

        if(frame.getArguments().length > 1){
            for(int i = 1; i < frame.getArguments().length; i++)
                setByType(args[i - 1], frame.getArguments()[i], frame);
        }

        Object result = mainNode.executeGeneric(frame);

        return result;
    }

}
