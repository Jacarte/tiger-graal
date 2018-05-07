package language.nodes.antlr.ast.block.functions;


import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.declaration.DeclarationNode;

public abstract class FuncDefinitionNode extends DeclarationNode {

    public String[] args = new String[0];


    public Object executeBuiltInt(Object[] args){
        return new NilValue();
    }

    public abstract ExpressionNode getBlock();

    public abstract long getNumberOfArguments();
}
