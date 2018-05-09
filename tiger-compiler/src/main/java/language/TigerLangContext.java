package language;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.MaterializedFrame;
import com.oracle.truffle.api.frame.VirtualFrame;
import language.nodes.antlr.ast.block.functions.FuncDeclarationNode;
import language.nodes.antlr.ast.block.functions.builtin.NanoTimeNode;
import language.nodes.antlr.ast.block.functions.builtin.PrintNode;
import language.nodes.antlr.ast.block.functions.builtin.WaitNode;

import java.util.HashMap;
import java.util.Map;

public final class TigerLangContext {

    private final FrameDescriptor globalFrameDescriptor;
    private final VirtualFrame globalFrame;

    public static TigerLangContext SINGLETON = new TigerLangContext();

    public TigerLangContext() {
        this.globalFrameDescriptor = new FrameDescriptor();
        this.globalFrame = Truffle.getRuntime().createVirtualFrame(new Object[]{null}, new FrameDescriptor());
    }


    public VirtualFrame getGlobalFrame(){
        return globalFrame;
    }



}