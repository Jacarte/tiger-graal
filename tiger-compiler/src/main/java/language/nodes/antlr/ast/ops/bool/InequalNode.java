package language.nodes.antlr.ast.ops.bool;

import com.oracle.truffle.api.dsl.Specialization;
import language.nodes.antlr.ast.ops.BinaryTigerNode;

public abstract class InequalNode extends BinaryTigerNode {


    @Specialization()
    public long neq(Object val0, Object val1){
        return  val0.equals(val1)? 0: 1;
    }
}
