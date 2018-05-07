package language.nodes.antlr.ast.ops.bool;

import com.oracle.truffle.api.dsl.Specialization;
import language.nodes.antlr.ast.ops.BinaryTigerNode;

public abstract class LessEqualNode extends BinaryTigerNode {


    @Specialization()
    public long le(long val0, long val1){
        return  val0 <= val1? 1: 0;
    }

    @Specialization()
    public long le(long val0, double val1){
        return  val0 <= val1? 1: 0;
    }

    @Specialization()
    public long le(double val0, long val1){
        return  val0 <= val1? 1: 0;
    }

    @Specialization()
    public long le(double val0, double val1){
        return  val0 <= val1? 1: 0;
    }
}
