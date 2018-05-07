package language.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.*;
import com.oracle.truffle.api.nodes.RootNode;
import language.nodes.antlr.ast.ExpressionNode;
import sun.jvm.hotspot.debugger.Address;

import java.util.Map;

public class TigerRootNode extends RootNode {

    @Child private ExpressionNode mainNode;
    @CompilerDirectives.CompilationFinal private String name;

    public TigerRootNode(TruffleLanguage<?> language, ExpressionNode node, FrameDescriptor descriptor) {
        super(language, descriptor);
        this.mainNode = node;
    }


    void setByType(String name, Object value, Frame fr){

        FrameSlot slot = fr.getFrameDescriptor().addFrameSlot(name);

        if(value instanceof Long){
            fr.setLong(slot, (long)value);
            slot.setKind(FrameSlotKind.Long);
        }
        else if(value instanceof Double) {
            fr.setDouble(slot, (double) value);
            slot.setKind(FrameSlotKind.Double);
        }
        else {
            fr.setObject(slot, value);
            slot.setKind(FrameSlotKind.Object);
        }
    }


    Frame getParentFrame(Frame fr){
        return (Frame)fr.getArguments()[0];
    }

    @Override
    public Object execute(VirtualFrame frame) {
        Map<String, Object> args = null;

        if(frame.getArguments().length > 0){

            args = (Map<String, Object>) frame.getArguments()[1];
            for(String k: args.keySet()){
                setByType(k, args.get(k), frame);
            }
        }
        /*if(frame.getArguments().length > 0)
            f = (VirtualFrame)frame.getArguments()[1];*/

        Object result = mainNode.executeGeneric(frame);

        return result;
    }

}
