package language.nodes.antlr.ast.declaration;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.block.functions.FuncDefinitionNode;

import java.lang.reflect.Type;

@NodeChild(value = "expr", type = ExpressionNode.class)
@NodeField(name = "id", type = String.class)
public abstract class VariableDeclarationNode extends DeclarationNode {


    public abstract String getId();

    public abstract ExpressionNode getExpr();


    @Override
    public Type getType() {
        return getExpr().getType();
    }

    @Specialization
    public String execute(VirtualFrame frame, String value){
        FrameSlot slot = defineSlot(frame, getId());
        frame.setObject(slot, value);
        slot.setKind(FrameSlotKind.Object);
        return value;
    }

    @Specialization
    public FuncDefinitionNode execute(VirtualFrame frame, FuncDefinitionNode value){

        FrameSlot slot = defineSlot(frame, getId());
        frame.setObject(slot, value);
        slot.setKind(FrameSlotKind.Object);

        //getScope().setObject(getId(), value);
        return value;
    }

    @Specialization
    public long executeLong(VirtualFrame frame, long value){

        FrameSlot slot = defineSlot(frame, getId());
        frame.setLong(slot, value);
        slot.setKind(FrameSlotKind.Long);

        //getScope().setLong(getId(), value);
        return value;
    }

    @Specialization
    public double executeDouble(VirtualFrame frame, double value){

        FrameSlot slot = defineSlot(frame, getId());
        frame.setObject(slot, value);
        slot.setKind(FrameSlotKind.Double);

        //getScope().setDouble(getId(), value);
        return value;
    }

}
