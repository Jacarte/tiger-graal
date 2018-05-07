package language.nodes.antlr.ast.conditional;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.api.profiles.ConditionProfile;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;

import java.lang.reflect.Type;

@NodeInfo(shortName = "if")
public class IfNode extends ExpressionNode {

    @Child ExpressionNode cond;
    @Child  ExpressionNode then;

    private final ConditionProfile condition = ConditionProfile.createCountingProfile();

    public IfNode(ExpressionNode cond, ExpressionNode then) {
        this.cond = cond;
        this.then = then;
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

        return new NilValue();
    }
}
