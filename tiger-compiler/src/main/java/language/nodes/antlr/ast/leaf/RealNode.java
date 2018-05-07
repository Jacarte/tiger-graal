package language.nodes.antlr.ast.leaf;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.VirtualFrame;
import org.antlr.v4.runtime.Token;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public abstract class RealNode extends TerminalNode {

    double val;

    public RealNode(double value) {
        super( null);
        this.val = value;
    }

    public RealNode(Token payload)
    {
        super( payload);
        this.val = Double.parseDouble(token.getText());
    }

    public double getValue(){
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }

    @Override
    public Type getType() {
        return Double.TYPE;
    }


    @Specialization
    public double executeReal(){
        CompilerAsserts.compilationConstant(val);
        return getValue();
    }

}
