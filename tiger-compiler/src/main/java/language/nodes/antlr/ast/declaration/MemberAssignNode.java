package language.nodes.antlr.ast.declaration;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.block.functions.FuncDefinitionNode;

import java.lang.reflect.Type;

@NodeChildren(value = {
        @NodeChild(value = "assign" , type = ExpressionNode.class)
})
@NodeField(name = "id", type = String.class)
public abstract class MemberAssignNode extends ExpressionNode {

    public abstract String getId();
    public abstract ExpressionNode getAssign();

    @Override
    public Type getType() {
        return getAssign().getType();
    }

    @Specialization
    public long executeLong(VirtualFrame frame, long result){

        writeUpStack(Frame::setLong, frame, getId(), result, FrameSlotKind.Long);

        return result;
    }

    @Specialization
    public String executeString(VirtualFrame frame,String result){

        writeUpStack(Frame::setObject, frame, getId(), result, FrameSlotKind.Object);
        return result;
    }

    @Specialization
    public FuncDefinitionNode executeFuncDefinition(VirtualFrame frame, FuncDefinitionNode result){


        writeUpStack(Frame::setObject, frame, getId(), result, FrameSlotKind.Object);

        return result;
    }

    @Specialization
    public double executeDouble(VirtualFrame frame, double result){

        writeUpStack(Frame::setDouble, frame, getId(), result, FrameSlotKind.Double);

        return result;
    }


    @Specialization
    public NilValue executeNil(VirtualFrame frame, NilValue result){

        writeUpStack(Frame::setObject, frame, getId(), result, FrameSlotKind.Object);

        return result;
    }

}
