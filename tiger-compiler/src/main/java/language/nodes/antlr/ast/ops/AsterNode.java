package language.nodes.antlr.ast.ops;

import com.oracle.truffle.api.dsl.Specialization;
import language.nodes.antlr.ast.exceptions.BreakException;

public abstract class AsterNode extends BinaryTigerNode {


    @Specialization(rewriteOn = BreakException.class)
    public long aster(long val0, long val1) throws BreakException
    {
        return  val0 * val1;
    }

    @Specialization(rewriteOn = BreakException.class)
    public double aster(long val0, double val1) throws BreakException
    {
        return  val0 * val1;
    }

    @Specialization(rewriteOn = BreakException.class)
    public double aster(double val0, long val1) throws BreakException
    {
        return  val0 * val1;
    }

    @Specialization(rewriteOn = BreakException.class)
    public double aster(double val0, double val1) throws BreakException
    {
        return  val0 * val1;
    }


    @Override
    public String toString() {
        return "*";
    }
}
