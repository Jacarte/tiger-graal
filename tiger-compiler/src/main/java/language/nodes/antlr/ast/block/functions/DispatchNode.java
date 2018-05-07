package language.nodes.antlr.ast.block.functions;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.IndirectCallNode;
import com.oracle.truffle.api.nodes.Node;
import language.nodes.antlr.ast.leaf.FuncRegistryNode;

public abstract class DispatchNode extends Node {


    public abstract Object executeDispatch(Object function, Object[] arguments);

    @Specialization
    @SuppressWarnings("unused")
    protected static Object doDirect(FuncRegistryNode function, Object[] arguments,
                                     @Cached("function.getCallTarget()") RootCallTarget callTarget) {

        /* Inline cache hit, we are safe to execute the cached call target. */
        return callTarget.call(arguments);
    }

}
