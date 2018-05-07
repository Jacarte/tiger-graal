package language.nodes.antlr.ast.ops.bool;

import com.oracle.truffle.api.dsl.Specialization;
import language.nodes.antlr.ast.exceptions.BreakException;
import language.nodes.antlr.ast.ops.BinaryTigerNode;

public abstract class EqualNode extends BinaryTigerNode {


    @Specialization(rewriteOn = BreakException.class)
    public long eq(Object val0, Object val1) throws BreakException
    {
        return val0.equals(val1)? 1: 0;
    }
}
