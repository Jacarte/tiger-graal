package language.nodes.antlr.ast.block.functions;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.*;
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

    FrameSlot slot;
    Frame scope;

    public FuncCallNode(String name, ExpressionNode[] args, FrameSlot slot, Frame scope){
        this.name = name;
        this.args = args;

        this.dispatchNode = DispatchNodeGen.create();
        this.slot = slot;
        this.scope = scope;
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
            obj = scope.getObject(slot);
        } catch (FrameSlotTypeException e) {
            e.printStackTrace();
        }


        Object[] exprResults = new Object[args.length + 1];
        for(int i = 0; i < args.length; i++) {
            exprResults[i + 1] = args[i].executeGeneric(frame);
        }

        exprResults[0] = frame.materialize();

        return dispatchNode.executeDispatch(obj, exprResults);

    }

}
