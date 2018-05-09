package language.nodes.antlr.ast.block.functions.builtin;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.block.functions.BuiltInCallNode;
import language.nodes.antlr.ast.block.functions.FuncDeclarationNode;
import language.nodes.antlr.ast.block.functions.FuncDefinitionNode;

import java.lang.reflect.Type;

@NodeInfo(shortName = "wait")
public class WaitNode extends ExpressionNode {

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {

        try {
            Thread.sleep((long)frame.getArguments()[1]);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new NilValue();
    }
}
