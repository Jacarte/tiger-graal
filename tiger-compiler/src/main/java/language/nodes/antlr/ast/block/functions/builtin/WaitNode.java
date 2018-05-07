package language.nodes.antlr.ast.block.functions.builtin;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import language.NilValue;
import language.nodes.antlr.ast.block.functions.BuiltInCallNode;

import java.lang.reflect.Type;

@NodeInfo(shortName = "wait")
public class WaitNode extends BuiltInCallNode {

    @Override
    @CompilerDirectives.TruffleBoundary
    public Object executeBuiltIn(Object[] args) {

        try {
            Thread.sleep((long)args[0]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return super.executeBuiltInt(args);
    }

    @Override
    public Type getType() {
        return NilValue.class;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return new NilValue();
    }

    @Override
    public long getNumberOfArguments() {
        return 1;
    }

    {
        args=new String[]{"millis"};
    }
}
