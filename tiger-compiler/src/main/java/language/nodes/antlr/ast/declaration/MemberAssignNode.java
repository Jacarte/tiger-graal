package language.nodes.antlr.ast.declaration;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.*;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.block.functions.FuncDefinitionNode;

import java.lang.reflect.Type;

@NodeChildren(value = {
        @NodeChild(value = "assign" , type = ExpressionNode.class)
})
@NodeField(name = "id", type = String.class)
@NodeField(name = "slot", type = FrameSlot.class)
@NodeField(name="scope", type = VirtualFrame.class)
public abstract class MemberAssignNode extends ExpressionNode {

    public abstract String getId();
    public abstract ExpressionNode getAssign();

    public abstract FrameSlot getSlot();
    public abstract VirtualFrame getScope();

    @Override
    public Type getType() {
        return getAssign().getType();
    }

    @Specialization
    public long executeLong(VirtualFrame frame, long result){


        getScope().setLong(getSlot(), result);
        getSlot().setKind(FrameSlotKind.Long);
        return result;
    }

    @Specialization
    public String executeString(VirtualFrame frame,String result){

        getScope().setObject(getSlot(), result);
        getSlot().setKind(FrameSlotKind.Object);
        return result;
    }

    @Specialization
    public FuncDefinitionNode executeFuncDefinition(VirtualFrame frame, FuncDefinitionNode result){


        getScope().setObject(getSlot(), result);
        getSlot().setKind(FrameSlotKind.Object);

        return result;
    }

    @Specialization
    public double executeDouble(VirtualFrame frame, double result){


        getScope().setDouble(getSlot(), result);
        getSlot().setKind(FrameSlotKind.Double);
        return result;
    }


    @Specialization
    public NilValue executeNil(VirtualFrame frame, NilValue result){
        getScope().setObject(getSlot(), result);
        getSlot().setKind(FrameSlotKind.Object);
        return result;
    }

}
