package language.nodes.antlr.ast.block.loop;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.assign.SimpleAssignNode;
import language.nodes.antlr.ast.block.loop.repeating.ForRepeatingNode;

import java.lang.reflect.Type;

public class ForNode extends LoopNode {

    public @Node.Child
    com.oracle.truffle.api.nodes.LoopNode loop;

    public ForNode(SimpleAssignNode init, ExpressionNode finalEx, ExpressionNode block){
        loop = Truffle.getRuntime().createLoopNode(new ForRepeatingNode(init, finalEx, block));
    }

    @Override
    public Type getType() {
        return NilValue.class;
    }

    @Override
    public final Object executeGeneric(VirtualFrame frame){
        VirtualFrame child = super.createChild(frame);

        loop.executeLoop(child);
        return new NilValue();
    }

}
