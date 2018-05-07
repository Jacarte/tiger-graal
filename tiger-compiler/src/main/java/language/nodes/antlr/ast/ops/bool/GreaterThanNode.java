package language.nodes.antlr.ast.ops.bool;

import com.oracle.truffle.api.dsl.Specialization;
import language.nodes.antlr.ast.ops.BinaryTigerNode;

public abstract class GreaterThanNode extends BinaryTigerNode {


    @Specialization()
    public long gte(long val0, long val1) {
        return val0 > val1 ? 1 : 0;
    }

    @Specialization()
    public long gte(long val0, double val1) {
        return val0 > val1 ? 1 : 0;
    }

    @Specialization()
    public long gte(double val0, long val1) {
        return val0 > val1 ? 1 : 0;
    }

    @Specialization()
    public long gte(double val0, double val1) {
        return val0 > val1 ? 1 : 0;
    }
}
