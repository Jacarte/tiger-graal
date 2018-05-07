package language.nodes.antlr.ast.conditional;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.profiles.ConditionProfile;
import language.nodes.antlr.ast.ExpressionNode;

import java.lang.reflect.Type;

@NodeInfo(shortName = "if_else")
public class IfThenElseNode extends ExpressionNode {

    @Child ExpressionNode cond;
    @Child ExpressionNode then;
    @Child ExpressionNode els;

    private final ConditionProfile condition = ConditionProfile.createCountingProfile();

    public IfThenElseNode(ExpressionNode cond, ExpressionNode then, ExpressionNode els) {
        this.cond = cond;
        this.then = then;
        this.els = els;
    }

    @Override
    public Type getType() {
        return then.getType();
    }

    @Override
    public Object executeGeneric(VirtualFrame frame){

        Object condResult = cond.executeGeneric(frame);

        boolean condition = false;

        if(condResult instanceof Double)
            condition = (double)condResult > 0;
        if(condResult instanceof Long)
            condition = (long)condResult > 0;

        if(this.condition.profile(condition)){
            return then.executeGeneric(frame);
        }
        else{
            return els.executeGeneric(frame);
        }

    }
}
