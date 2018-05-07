package language.nodes.antlr.ast.ops;

import com.oracle.truffle.api.dsl.Specialization;

public abstract class DivNode extends BinaryTigerNode {


    @Specialization()
    public long div(long val0, long val1){
        return  val0 / val1;
    }

    @Specialization()
    public double div(double val0, long val1){
        return  val0 / val1;
    }

    @Specialization()
    public double div(long val0, double val1){
        return  val0 / val1;
    }

    @Specialization()
    public double div(double val0, double val1){
        return  val0 / val1;
    }

    @Override
    public String toString() {
        return "/";
    }
}
