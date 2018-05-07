package language.nodes.antlr.ast.conditional;

import com.oracle.truffle.api.frame.VirtualFrame;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;

//import com.xppcoder.parser.ast.nodes.ExpressionNode

import java.lang.reflect.Type;

public class DebugNode extends ExpressionNode {

    @Override
    public Type getType() {
        return NilValue.class;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {

        return new NilValue();
    }
}
