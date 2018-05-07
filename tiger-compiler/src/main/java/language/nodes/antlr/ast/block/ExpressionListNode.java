package language.nodes.antlr.ast.block;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;

import java.lang.reflect.Type;

public class ExpressionListNode extends ExpressionNode {

    @Children final ExpressionNode[] list;

    public ExpressionListNode(ExpressionNode[] list) {
        this.list = list;
    }

    @Override
    public Type getType() {
        return  list.length > 0 ? list[list.length - 1].getType(): NilValue.class;
    }

    @Override
    @ExplodeLoop
    public Object executeGeneric(VirtualFrame frame){

        Object result = new NilValue();

        CompilerAsserts.compilationConstant(list.length);

        for(int i = 0; i < list.length; i++){
            result = list[i].executeGeneric(frame);
        }

        return result;
    }
}
