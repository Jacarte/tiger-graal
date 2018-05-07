package language.nodes.antlr.ast;

import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.RootNode;

public class MainNode extends RootNode {



    @Child private DirectCallNode directCallNode;

    public MainNode(TruffleLanguage<?> language, RootCallTarget toExecute) {
        super(language);
        directCallNode = toExecute != null? DirectCallNode.create(toExecute) : null;
    }

    @Override
    public Object execute(VirtualFrame frame) {
        return directCallNode.call(null);
    }
}
