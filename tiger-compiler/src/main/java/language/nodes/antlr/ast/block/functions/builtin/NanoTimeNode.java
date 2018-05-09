package language.nodes.antlr.ast.block.functions.builtin;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.block.functions.BuiltInCallNode;

import java.lang.reflect.Type;

@NodeInfo(shortName = "nowtime")
public abstract class NanoTimeNode extends ExpressionNode {


    @Override
    public Type getType() {
        return null;
    }

    @Specialization
    public long readLong(VirtualFrame frame) {
        return System.nanoTime();
    }
}
