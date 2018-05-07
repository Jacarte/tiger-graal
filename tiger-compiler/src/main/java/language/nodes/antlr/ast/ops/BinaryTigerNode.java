package language.nodes.antlr.ast.ops;

import com.oracle.truffle.api.dsl.NodeChild;
import com.oracle.truffle.api.dsl.NodeChildren;
import language.nodes.antlr.ast.ExpressionNode;

import java.lang.reflect.Type;

@NodeChildren({@NodeChild(value = "leftNode", type = ExpressionNode.class), @NodeChild(value = "rightNode", type = ExpressionNode.class)})
public abstract class BinaryTigerNode extends ExpressionNode {


    public abstract ExpressionNode getLeftNode();
    public abstract ExpressionNode getRightNode();

    @Override
    public Type getType() {
        return Long.TYPE;
    }
}
