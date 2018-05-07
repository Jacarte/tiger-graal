package language.nodes.antlr.ast.assign;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import language.nodes.antlr.ast.ExpressionNode;

import java.lang.reflect.Type;

@NodeChildren({@NodeChild(value="expr", type = ExpressionNode.class)})
public abstract class SimpleAssignNode extends ExpressionNode {

    @CompilerDirectives.CompilationFinal String id;

    public SimpleAssignNode(String id){
        this.id = id;
    }

    @Specialization()
    public long executeInt(VirtualFrame frame, long expr){
        // frame.setLong(getSlot(frame, id), expr);
        FrameSlot slot = defineSlot(frame, id);
        slot.setKind(FrameSlotKind.Long);
        return expr;
    }

    @Override
    public Type getType() {
        return Long.class;
    }


    public String getVarId(){
        return id;
    }

}
