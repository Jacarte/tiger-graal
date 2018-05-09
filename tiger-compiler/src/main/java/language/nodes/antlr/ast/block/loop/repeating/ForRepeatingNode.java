package language.nodes.antlr.ast.block.loop.repeating;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.FrameSlot;
import com.oracle.truffle.api.frame.FrameSlotKind;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RepeatingNode;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.api.profiles.BranchProfile;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.assign.SimpleAssignNode;
import language.nodes.antlr.ast.exceptions.BreakException;

public class ForRepeatingNode extends Node implements RepeatingNode {



    @CompilerDirectives.CompilationFinal long init = 0;
    @CompilerDirectives.CompilationFinal long end = 0;


    @Child ExpressionNode body;
    long it = 0;
    String varId;

    /**
     * Profiling information, collected by the interpreter, capturing whether a {@code continue}
     * statement was used in this loop. This allows the compiler to generate better code for loops
     * without a {@code continue}.
     */
    final BranchProfile breakTaken = BranchProfile.create();

    public interface IAction{
        void doit(long assign);
    }

    IAction action;

    public ForRepeatingNode(String varId, long init, long end, ExpressionNode body, IAction action) {

        it = init - 1;
        this.end = end;
        this.varId = varId;
        this.body = body;

        this.action = action;
    }

    @Override
    public boolean executeRepeating(VirtualFrame frame) {

        if(!evaluateCondition(frame)){
            return false;
        }

        try{
            body.executeGeneric(frame);

            return true;
        }
        catch (BreakException e){

            breakTaken.enter();
            it = -1;
            return false;
        }
    }


    public boolean evaluateCondition(VirtualFrame frame){

        if(it ==  end - 1) {
            it = -1;
            return false;
        }

        it++;

        action.doit(it);

        return true;

    }



}
