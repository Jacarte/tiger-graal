package language;

import com.oracle.truffle.api.dsl.TypeSystem;
import language.nodes.antlr.ast.block.functions.FuncDefinitionNode;

@TypeSystem({long.class, String.class, double.class, FuncDefinitionNode.class, NilValue.class})
public abstract class TigerTypes {

}
