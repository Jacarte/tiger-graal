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

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class FuncCallNode extends ExpressionNode {

    @CompilerDirectives.CompilationFinal String name;
    @Children final ExpressionNode[] args;

    @Child DispatchNode dispatchNode;

    public FuncCallNode(String name, ExpressionNode[] args, DispatchNode dispatchNode){
        this.name = name;
        this.args = args;

        this.dispatchNode = dispatchNode;
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

        FuncDefinitionNode obj = null;
        try {
            Object tocast =  readUpStack(Frame::getObject, frame, name);
            obj = (FuncDefinitionNode)tocast;
        } catch (FrameSlotTypeException e) {
            e.printStackTrace();
        }

        Map<String, Object> argValues = new HashMap<>();

        //System.out.print("calling function with ");

        Object[] exprResults = new Object[args.length];
        for(int i = 0; i < obj.getNumberOfArguments(); i++) {
            exprResults[i] = args[i].executeGeneric(frame);
        }

        //argValues[0] = getScope();

//        System.out.println("");


        if(obj instanceof BuiltInCallNode)
            result = ((BuiltInCallNode) obj).executeBuiltIn(exprResults);
        else if(obj instanceof FuncDeclarationNode) {

            //setupStack(obj.getScope(), obj.args);
            for(int i = 0; i < obj.getNumberOfArguments(); i++) {
                argValues.put(obj.args[i], exprResults[i]);
            }

            TigerRootNode root = new TigerRootNode(((FuncDeclarationNode) obj).lang, obj.getBlock(),
                    new FrameDescriptor());

            //IndirectCallNode in = Truffle.getRuntime().createIndirectCallNode();

            result = Truffle.getRuntime().createDirectCallNode(
                    Truffle.getRuntime().createCallTarget(root)
            ).call(new Object[]{frame, argValues});

            //result = in.call(Truffle.getRuntime().createCallTarget(root) , );


            //result = obj.getBlock().executeGeneric(frame);

            //cleanStack(obj.getScope(), obj.args);
        }
        //System.out.println("  Resulting " + result);

        return result;
    }

}
