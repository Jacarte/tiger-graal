package language.nodes.antlr.ast.leaf;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.antlr.v4.runtime.Token;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class IntegerNode extends TerminalNode {


    long value;

    public IntegerNode(long value){
        super( null);
        this.value = value;
    }
    public IntegerNode(Token payload) {

        super( payload);
        value = Long.parseLong(token.getText());
    }

    public long getValue(){
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    @Override
    public Type getType() {
        return Long.TYPE;
    }


    @Specialization
    public long executeLong()
    {
        return getValue();
    }

}
