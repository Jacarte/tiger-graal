package language.nodes.antlr.ast.block.functions;


import language.nodes.antlr.ast.ExpressionNode;

public abstract class BuiltInCallNode extends FuncDefinitionNode {

    public Object executeBuiltIn(Object[] args){
        return 0;
    }

    @Override
    public ExpressionNode getBlock() {
        return null;
    }
}
