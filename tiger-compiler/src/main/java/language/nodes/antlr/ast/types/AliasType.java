package language.nodes.antlr.ast.types;

public class AliasType extends TigerType {

    String type;
    public void setType(String type){
        this.type = type;
    }
    public String getTtypeName() {
        return type;
    }
}
