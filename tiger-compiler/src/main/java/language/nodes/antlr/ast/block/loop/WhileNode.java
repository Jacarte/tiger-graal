package language.nodes.antlr.ast.block.loop;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.block.loop.repeating.WhileRepeatinNode;

import java.lang.reflect.Type;

@NodeInfo(shortName = "while")
public class WhileNode extends LoopNode {

    @Child
    com.oracle.truffle.api.nodes.LoopNode loopNode;

    public WhileNode(ExpressionNode condition, ExpressionNode block) {
        loopNode = Truffle.getRuntime().createLoopNode(new WhileRepeatinNode(condition, block));
    }

    @Override
    public Type getType() {
        return NilValue.class;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        loopNode.executeLoop(frame);
        return new NilValue();
    }

}
