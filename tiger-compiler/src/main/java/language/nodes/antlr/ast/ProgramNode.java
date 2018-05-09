package language.nodes.antlr.ast;

import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.Node;
import com.oracle.truffle.api.nodes.NodeInfo;
import language.nodes.antlr.ast.block.functions.builtin.NanoTimeNode;
import language.nodes.antlr.ast.block.functions.builtin.PrintNode;
import language.nodes.antlr.ast.block.functions.builtin.WaitNode;

import java.lang.reflect.Type;

@NodeInfo(shortName = "program")
public class ProgramNode extends ExpressionNode {

    @Node.Child
    ExpressionNode expression;

    public ProgramNode(ExpressionNode expression){
        this.expression = expression;
        expression.setParent(this);
    }


    @Override
    public Type getType() {
        return expression.getType();
    }


    @Override
    public Object executeGeneric(VirtualFrame frame) {


        return expression.executeGeneric(frame);
    }
}
