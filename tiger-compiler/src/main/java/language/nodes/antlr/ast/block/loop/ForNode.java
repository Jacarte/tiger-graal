package language.nodes.antlr.ast.block.loop;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.MaterializedFrame;
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

    FrameSlot slot;
    MaterializedFrame scope;

    public ForNode(String varId, ExpressionNode init, ExpressionNode finalEx, ExpressionNode block, FrameSlot slot, MaterializedFrame frame){
        this.varId = varId;
        this.end = finalEx;
        this.init = init;
        this.block = block;

        this.slot = slot;
        this.scope = frame;
    }

    @Override
    public Type getType() {
        return NilValue.class;
    }

    @Override
    public final Object executeGeneric(VirtualFrame frame){

        long init = 0;
        long end = 0;

        try {
            init = this.init.executeLong(frame);
            end = this.end.executeLong(frame);

        } catch (UnexpectedResultException e) {
            e.printStackTrace();
        }

        loop = Truffle.getRuntime().createLoopNode(new ForRepeatingNode(varId, init, end, block, (assign) -> {
            scope.setLong(slot, assign);
        }));

        loop.executeLoop(frame);

        return new NilValue();
    }

}
