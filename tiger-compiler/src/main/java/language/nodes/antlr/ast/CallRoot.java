package language.nodes.antlr.ast;

import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.RootNode;

public class CallRoot extends RootNode {



    protected CallRoot(TruffleLanguage<?> language, ExpressionNode body, String[] argNames) {
        super(language);
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return null;
    }
}
