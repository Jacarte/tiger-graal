package language.nodes.antlr.ast.block.loop.repeating;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RepeatingNode;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.api.profiles.BranchProfile;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.assign.SimpleAssignNode;
import language.nodes.antlr.ast.exceptions.BreakException;

public class ForRepeatingNode extends Node implements RepeatingNode {


    @Child
    ExpressionNode block;
    @Child
    SimpleAssignNode initBlock;
    @Child ExpressionNode endBlock;

    @CompilerDirectives.CompilationFinal long init = 0;
    @CompilerDirectives.CompilationFinal long end = 0;

    long it = 0;

    /**
     * Profiling information, collected by the interpreter, capturing whether a {@code continue}
     * statement was used in this loop. This allows the compiler to generate better code for loops
     * without a {@code continue}.
     */
    final BranchProfile breakTaken = BranchProfile.create();

    public ForRepeatingNode(SimpleAssignNode init, ExpressionNode end, ExpressionNode body) {
        this.block = body;
        this.initBlock = init;
        this.endBlock = end;

        it = -1;
    }

    @Override
    public boolean executeRepeating(VirtualFrame frame) {

        if(!evaluateCondition(frame)){
            return false;
        }

        try{
            block.executeGeneric(frame);

            return true;
        }
        catch (BreakException e){

            breakTaken.enter();
            it = -1;
            return false;
        }
    }


    public boolean evaluateCondition(VirtualFrame frame){

        if(it >=  end) {
            it = -1;
            return false;
        }

        if(it == -1){ // init loop

            try {
                init = initBlock.executeLong(frame);
                end = endBlock.executeLong(frame) - 1;
            } catch (UnexpectedResultException e) {
                e.printStackTrace();
            }

            it = init - 1;

        }

        it++;
        frame.setLong(initBlock.getSlot(frame, initBlock.getVarId()), it);

        return true;

    }



}
