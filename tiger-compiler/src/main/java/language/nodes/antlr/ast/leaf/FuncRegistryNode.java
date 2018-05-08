package language.nodes.antlr.ast.leaf;

import com.oracle.truffle.api.Assumption;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.utilities.CyclicAssumption;
import language.TigerLang;

public class FuncRegistryNode {

    public final String name;


    public FuncRegistryNode(TigerLang lang, String name) {
        this.name = name;
    }
}
