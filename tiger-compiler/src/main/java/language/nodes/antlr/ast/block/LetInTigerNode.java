package language.nodes.antlr.ast.block;

import com.oracle.truffle.api.CompilerAsserts;
import com.oracle.truffle.api.frame.VirtualFrame;
import com.oracle.truffle.api.nodes.ExplodeLoop;
import language.NilValue;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.declaration.DeclarationNode;

import java.lang.reflect.Type;


public class LetInTigerNode extends ExpressionNode {

    @Children protected final ExpressionNode[] InNode;

    @Children protected final DeclarationNode[] Declarations;

    @Override
    public Type getType() {
        return InNode.length > 0? InNode[InNode.length - 1].getType(): NilValue.class;
    }

    @Override
    @ExplodeLoop
    public Object executeGeneric(VirtualFrame frame){


        CompilerAsserts.compilationConstant(InNode.length);
        CompilerAsserts.compilationConstant(Declarations.length);

        for(int i = 0; i < Declarations.length; i++)
            Declarations[i].executeGeneric(frame);

        for(int i = 0; i < InNode.length - 1; i++)
            InNode[i].executeGeneric(frame);

        if(InNode.length > 0)
            return InNode[InNode.length - 1].executeGeneric(frame);



        return new NilValue();
    }

    public LetInTigerNode(ExpressionNode[] inNode, DeclarationNode[] declarations){
        this.InNode = inNode;
        this.Declarations = declarations;

    }

}
