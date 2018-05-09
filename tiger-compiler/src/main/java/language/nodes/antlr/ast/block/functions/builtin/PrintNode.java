package language.nodes.antlr.ast.block.functions.builtin;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.NodeInfo;
import language.NilValue;
import language.TigerLang;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.block.functions.BuiltInCallNode;
import language.nodes.antlr.ast.block.functions.FuncDeclarationNode;

import java.lang.reflect.Type;

@NodeInfo(shortName = "print")
public class PrintNode extends ExpressionNode {


    @Override
    public Type getType() {
        return null;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame) {
        System.out.print(frame.getArguments()[1]);
        System.out.flush();
        return new NilValue();
    }
}
