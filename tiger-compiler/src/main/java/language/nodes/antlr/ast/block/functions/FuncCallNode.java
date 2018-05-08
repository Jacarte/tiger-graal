package language.nodes.antlr.ast.block.functions;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.FrameSlotTypeException;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import language.NilValue;
import language.nodes.TigerRootNode;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.leaf.FuncRegistryNode;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class FuncCallNode extends ExpressionNode {

    @CompilerDirectives.CompilationFinal String name;
    @Children final ExpressionNode[] args;

    @Child DispatchNode dispatchNode;

    public FuncCallNode(String name, ExpressionNode[] args){
        this.name = name;
        this.args = args;

        this.dispatchNode = DispatchNodeGen.create();
    }

    @Override
    public Type getType() {
        return NilValue.class;
    }

    @Override
    @ExplodeLoop
    public Object executeGeneric(VirtualFrame frame)
    {

        CompilerAsserts.compilationConstant(args.length);


        Object obj = null;
        try {
            obj = readUpStack(Frame::getObject, frame, name);
        } catch (FrameSlotTypeException e) {
            e.printStackTrace();
        }


        Object[] exprResults = new Object[args.length + 1];
        for(int i = 0; i < args.length; i++) {
            exprResults[i + 1] = args[i].executeGeneric(frame);
        }

        exprResults[0] = frame;

        if(obj instanceof FuncDeclarationNode)
            return dispatchNode.executeDispatch(obj, exprResults);
        else
            return ((BuiltInCallNode)obj).executeBuiltIn(exprResults);
    }

}
