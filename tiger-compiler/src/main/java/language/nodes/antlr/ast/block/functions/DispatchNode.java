package language.nodes.antlr.ast.block.functions;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.dsl.Cached;
import com.oracle.truffle.api.dsl.Fallback;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.interop.*;
import com.oracle.truffle.api.nodes.DirectCallNode;
import com.oracle.truffle.api.nodes.IndirectCallNode;
import com.oracle.truffle.api.nodes.Node;
import language.nodes.antlr.ast.leaf.FuncRegistryNode;

public abstract class DispatchNode extends Node {


    public static final int INLINE_CACHE_SIZE = 2;

    public abstract Object executeDispatch(Object function, Object[] arguments);


    @Specialization(limit = "INLINE_CACHE_SIZE", //
            guards = "function.getCallTarget() == cachedTarget", //
            assumptions = "callTargetStable")
    @SuppressWarnings("unused")
    protected static Object doDirect(FuncDeclarationNode function, Object[] arguments,
                                     @Cached("function.getCallTargetStable()") Assumption callTargetStable,
                                     @Cached("function.getCallTarget()") RootCallTarget cachedTarget,
                                     @Cached("create(cachedTarget)") DirectCallNode callNode) {

        /* Inline cache hit, we are safe to execute the cached call target. */
        return callNode.call(arguments);
    }


    @Specialization(replaces = "doDirect")
    protected static Object doIndirect(FuncDeclarationNode function, Object[] arguments,
                                       @Cached("create()") IndirectCallNode callNode) {
        return callNode.call(function.getCallTarget(), arguments);
    }

}
