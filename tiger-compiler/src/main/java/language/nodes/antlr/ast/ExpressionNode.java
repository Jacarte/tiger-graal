package language.nodes.antlr.ast;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.*;
import com.oracle.truffle.api.nodes.UnexpectedResultException;
import language.NilValue;
import language.TigerTypesGen;
import language.nodes.antlr.ast.block.functions.FuncDefinitionNode;
import language.nodes.antlr.ast.leaf.IdNode;
import language.nodes.antlr.ast.scopes.ScopeException;
import language.services.ILookupProvider;
import language.services.LookupProviderFactory;

import java.lang.reflect.Type;

public abstract class ExpressionNode extends BaseTigerNode {



    public abstract Type getType();

    public abstract Object executeGeneric(VirtualFrame frame);

    public long executeLong(VirtualFrame virtualFrame)
            throws UnexpectedResultException {
        return TigerTypesGen.expectLong(
                this.executeGeneric(virtualFrame));
    }


    public double executeDouble(VirtualFrame virtualFrame)
            throws UnexpectedResultException {
        return TigerTypesGen.expectDouble(
                this.executeGeneric(virtualFrame));
    }


}
