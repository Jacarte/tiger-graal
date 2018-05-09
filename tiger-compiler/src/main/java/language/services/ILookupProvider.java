package language.services;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.*;
import javafx.util.Pair;
import language.nodes.antlr.ast.block.functions.ArgDeclaration;
import language.nodes.antlr.ast.block.functions.ArgNode;
import language.nodes.antlr.ast.leaf.IdNode;
import language.nodes.antlr.ast.scopes.ScopeException;

import java.util.Map;

public interface ILookupProvider {

    ILookupProvider getParent();

    VirtualFrame getFrame();

    ILookupProvider createChild(ILookupProvider parent);

    FrameSlot defineSlot(String name);

    FrameSlot getThisSlot(String name);

    Pair<VirtualFrame, FrameSlot> findUp(String name);

}
