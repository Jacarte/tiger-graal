package language.nodes.antlr.ast.block.loop.repeating;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.RepeatingNode;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import com.oracle.truffle.api.profiles.BranchProfile;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.exceptions.BreakException;

public class WhileRepeatinNode extends Node implements RepeatingNode {

    @Child
    ExpressionNode block;
    @Child ExpressionNode condition;


    private final BranchProfile breakTaken = BranchProfile.create();

    public WhileRepeatinNode(ExpressionNode condition, ExpressionNode blockNode){
        this.condition = condition;
        this.block = blockNode;
    }

    @Override
    public boolean executeRepeating(VirtualFrame frame) {

        if(!executeCondition(frame))
            return false;

        try{
            block.executeGeneric(frame);

            return true;
        }
        catch (BreakException e){
            breakTaken.enter();

            return false;
        }
    }

    boolean executeCondition(VirtualFrame frame){

        try{
            long result = condition.executeLong(frame);

            return result > 0;
        } catch (UnexpectedResultException e) {
            e.printStackTrace();

            return false;
        }

    }


}
