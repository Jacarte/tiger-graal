package language.services.providers;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.*;
import javafx.util.Pair;
import language.nodes.antlr.ast.block.functions.ArgDeclaration;
import language.nodes.antlr.ast.block.functions.ArgNode;
import language.nodes.antlr.ast.leaf.IdNode;
import language.nodes.antlr.ast.scopes.ScopeException;
import language.services.ILookupProvider;

import java.util.HashMap;
import java.util.Map;

public class NaiveLookupProvider implements ILookupProvider {

    ILookupProvider parent;
    VirtualFrame frame;

    public NaiveLookupProvider(ILookupProvider parent, VirtualFrame frame){
        this.parent = parent;
        this.frame = frame;
    }

    @Override
    public ILookupProvider getParent() {
        return parent;
    }

    @Override
    public VirtualFrame getFrame() {
        return frame;
    }

    @Override
    public ILookupProvider createChild(ILookupProvider parent) {
        return new NaiveLookupProvider(this, Truffle.getRuntime().createVirtualFrame(new Object[]{null}, new FrameDescriptor()));
    }

    @Override
    public FrameSlot defineSlot(String name) {
        return getFrame().getFrameDescriptor().addFrameSlot(name);
    }

    @Override
    public FrameSlot getThisSlot(String name) {
        return getFrame().getFrameDescriptor().findFrameSlot(name);
    }

    @Override
    public Pair<VirtualFrame, FrameSlot> findUp(String name) {

        FrameSlot slot = frame.getFrameDescriptor().findFrameSlot(name);

        ILookupProvider parent = this;
        VirtualFrame fr = this.getFrame();

        while(slot == null){

            parent = parent.getParent();

            if(parent == null)
                throw new ScopeException("The variable is undefined " + name);
            fr = parent.getFrame();

            slot = parent.getFrame().getFrameDescriptor().findFrameSlot(name);
        }

        return new Pair<>(fr, slot);

    }


}
