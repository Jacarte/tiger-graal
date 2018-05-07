package language.nodes.antlr.ast.leaf;

import com.oracle.truffle.api.frame.VirtualFrame;
import language.NilValue;
import org.antlr.v4.runtime.Token;

import java.lang.reflect.Type;

public class NilNode extends TerminalNode {
    public NilNode(Token payload) {
        super( payload);
    }

    @Override
    public Type getType() {
        return NilValue.class;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        return new NilValue();
    }
}
