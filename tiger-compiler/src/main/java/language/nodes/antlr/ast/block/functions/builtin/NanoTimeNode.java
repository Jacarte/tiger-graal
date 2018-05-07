package language.nodes.antlr.ast.block.functions.builtin;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import language.nodes.antlr.ast.block.functions.BuiltInCallNode;

import java.lang.reflect.Type;

@NodeInfo(shortName = "nowtime")
public class NanoTimeNode extends BuiltInCallNode {

    @Override
    public Type getType() {
        return Long.TYPE;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return System.currentTimeMillis();
    }

    @Override
    @CompilerDirectives.TruffleBoundary
    public Object executeBuiltIn(Object[] args) {
        return System.nanoTime();
    }

    @Override
    public long getNumberOfArguments() {
        return 0;
    }

    {
        args = new String[0];
    }
}
