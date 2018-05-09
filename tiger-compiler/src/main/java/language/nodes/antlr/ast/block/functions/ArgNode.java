package language.nodes.antlr.ast.block.functions;

import com.oracle.truffle.api.dsl.NodeField;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;

import java.lang.reflect.Type;

@NodeField(name = "index", type = Integer.class)
@NodeField(name = "depth", type = Integer.class)
public abstract class ArgNode extends ExpressionNode {

    public abstract int getIndex();
    public abstract int getDepth();

    public String name;

    @Specialization(guards = {"isLong(frame)"})
    public long readlong(VirtualFrame frame){
        return (Long)getScope(frame).getArguments()[getIndex() + 1];
    }

    @Specialization(guards = {"isDouble(frame)"})
    public double readDouble(VirtualFrame frame){
        return (Double) getScope(frame).getArguments()[getIndex() + 1];
    }

    @Specialization(guards = {"isFuncDeclaration(frame)"})
    public FuncDeclarationNode readFuncDeclaration(VirtualFrame frame){
        return (FuncDeclarationNode)getScope(frame).getArguments()[getIndex() + 1];
    }

    boolean isLong(VirtualFrame frame){
        return getScope(frame).getArguments()[getIndex() + 1] instanceof Long;
    }


    boolean isDouble(VirtualFrame frame){
        return getScope(frame).getArguments()[getIndex() + 1] instanceof Double;
    }


    boolean isFuncDeclaration(VirtualFrame frame){
        return getScope(frame).getArguments()[getIndex() + 1] instanceof FuncDeclarationNode;
    }

    Frame getScope(Frame fr){

        Frame upFrame = fr;

        for(int i = 0; i < getDepth(); i++){
            upFrame = (Frame)fr.getArguments()[0];
        }

        return upFrame;
    }

    @Override
    public Type getType() {
        return null;
    }
}
