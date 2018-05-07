package language.nodes.antlr.ast.ops.bool;

import com.oracle.truffle.api.dsl.Specialization;
import language.nodes.antlr.ast.ops.BinaryTigerNode;

public abstract class BitOrNode extends BinaryTigerNode {



    @Specialization()
    public long or(long val0, long val1){
        return  val0 | val1;
    }
}
