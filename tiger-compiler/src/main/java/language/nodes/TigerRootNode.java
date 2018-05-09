package language.nodes;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.frame.*;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import com.oracle.truffle.api.nodes.RootNode;
import language.nodes.antlr.ast.ExpressionNode;
import sun.jvm.hotspot.debugger.Address;

import java.util.Map;

public class TigerRootNode extends RootNode {

    @Child private ExpressionNode mainNode;
    @CompilerDirectives.CompilationFinal private String name;

    public TigerRootNode(TruffleLanguage<?> language, FrameDescriptor descriptor, ExpressionNode node) {
        super(language, descriptor);
        this.mainNode = node;
    }

    @Override
    @ExplodeLoop
    public Object execute(VirtualFrame frame) {
        return mainNode.executeGeneric(frame);
    }

}
