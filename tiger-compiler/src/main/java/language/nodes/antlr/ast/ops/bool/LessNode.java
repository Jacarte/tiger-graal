package language.nodes.antlr.ast.ops.bool;

import com.oracle.truffle.api.dsl.Specialization;
import language.nodes.antlr.ast.exceptions.BreakException;
import language.nodes.antlr.ast.ops.BinaryTigerNode;

public abstract class LessNode extends BinaryTigerNode {


    @Specialization(rewriteOn = BreakException.class)
    public long ls(long val0, long val1) throws BreakException
    {
        return  val0 < val1? 1: 0;
    }

    @Specialization(rewriteOn = BreakException.class)
    public long ls(long val0, double val1)throws BreakException
    {
        return  val0 < val1? 1: 0;
    }

    @Specialization(rewriteOn = BreakException.class)
    public long ls(double val0, long val1) throws BreakException
    {
        return  val0 < val1? 1: 0;
    }

    @Specialization(rewriteOn = BreakException.class)
    public long ls(double val0, double val1) throws BreakException
    {
        return  val0 < val1? 1: 0;
    }
}
