package language.nodes.antlr.ast.ops;

import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;

@NodeInfo(shortName = "-")
public abstract class MinusNode extends BinaryTigerNode {


    @Override
    public String toString() {
        return "-";
    }

    @Specialization()
    public long minus(long val0, long val1){
        return  val0 - val1;
    }


    @Specialization()
    public double minus(long val0, double val1){
        return  val0 - val1;
    }


    @Specialization()
    public double minus(double val0, long val1){
        return  val0 - val1;
    }


    @Specialization()
    public double minus(double val0, double val1){
        return  val0 - val1;
    }
}
