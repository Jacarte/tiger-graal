package language.nodes.antlr.ast.leaf;

import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.types.TigerType;
import org.antlr.v4.runtime.Token;

public abstract class TerminalNode extends ExpressionNode {

    protected Token token;
    protected TigerType type;

    public void setType(TigerType type){
        this.type = type;
    }

    public TerminalNode(Token payload){
        this.token = payload;
    }

}
