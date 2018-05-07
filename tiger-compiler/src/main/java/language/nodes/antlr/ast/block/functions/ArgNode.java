package language.nodes.antlr.ast.block.functions;

import com.oracle.truffle.api.frame.VirtualFrame;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;

import java.lang.reflect.Type;

public class ArgNode extends ExpressionNode {

    public String name;


    @Override
    public Type getType() {
        return NilValue.class;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame){
        return new NilValue();
    }
}
