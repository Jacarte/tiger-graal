package language.nodes.antlr.ast;

import com.oracle.truffle.api.nodes.Node;

public abstract class BaseTigerNode extends Node {


    public BaseTigerNode parent;


    public void setParent(BaseTigerNode parent){
        this.parent = parent;
    }
}
