package language.nodes.antlr.ast.block.loop;

import com.oracle.truffle.api.frame.VirtualFrame;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.exceptions.BreakException;

import java.lang.reflect.Type;

public class BreakNode extends ExpressionNode {

    @Override
    public Type getType() {
        return NilValue.class;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame){
        //throw new BreakException();
        throw BreakException.SINGLETON;
    }
}
