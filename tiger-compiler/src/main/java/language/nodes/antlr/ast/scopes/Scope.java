package language.nodes.antlr.ast.scopes;

import language.nodes.antlr.ast.declaration.DeclarationNode;

import java.util.*;

public class Scope {

    List<DeclarationNode> declarations;

    public Scope(){
        declarations = new ArrayList<>();
    }

    public List<DeclarationNode> getDeclarations(){
        return declarations;
    }

}
