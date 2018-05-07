package language.nodes.antlr.ast.leaf;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.antlr.v4.runtime.Token;

import java.lang.reflect.Type;

public abstract class StringNode extends TerminalNode {

    public StringNode(Token payload)
    {
        super( payload);
        this.value = token.getText().substring(1, token.getText().length() - 1);
    }

    String value;

    @Override
    public Type getType() {
        return String.class;
    }

    @Override
    public Object executeGeneric(VirtualFrame frame){

        return getValue();
    }

    public String getValue(){
        return value; // Removing
    }

    @Specialization
    public String executeString(){
        CompilerAsserts.compilationConstant(value);
        return getValue();
    }
}
