package language.nodes.antlr.ast.block.loop;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.assign.SimpleAssignNode;
import language.nodes.antlr.ast.block.loop.repeating.ForRepeatingNode;

import java.lang.reflect.Type;

public class ForNode extends LoopNode {

    public @Node.Child
    com.oracle.truffle.api.nodes.LoopNode loop;

    @Child
    ExpressionNode init;
    @Child
    ExpressionNode end;
    @Child
    ExpressionNode block;


    String varId;

    public ForNode(String varId, ExpressionNode init, ExpressionNode finalEx, ExpressionNode block){
        this.varId = varId;
        this.end = finalEx;
        this.init = init;
        this.block = block;
    }

    @Override
    public Type getType() {
        return NilValue.class;
    }

    @Override
    public final Object executeGeneric(VirtualFrame frame){
        VirtualFrame child = super.createChild(frame);

        long init = 0;
        long end = 0;
        defineSlot(child, varId);

        try {
            init = this.init.executeLong(child);
            end = this.end.executeLong(child);

        } catch (UnexpectedResultException e) {
            e.printStackTrace();
        }

        loop = Truffle.getRuntime().createLoopNode(new ForRepeatingNode(varId, init, end, block));

        loop.executeLoop(child);

        return new NilValue();
    }

}
