package language.nodes.antlr.ast.declaration;

import com.oracle.truffle.api.frame.VirtualFrame;
import language.NilValue;

import java.lang.reflect.Type;

public class TypeDeclaration extends DeclarationNode {



    @Override
    public Type getType() {
        return NilValue.class;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame){
        return null;
    }
}
