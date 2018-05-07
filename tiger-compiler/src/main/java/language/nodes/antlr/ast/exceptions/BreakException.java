package language.nodes.antlr.ast.exceptions;

import com.oracle.truffle.api.nodes.ControlFlowException;

public class BreakException extends ControlFlowException {

    public static final BreakException SINGLETON = new BreakException();
}
