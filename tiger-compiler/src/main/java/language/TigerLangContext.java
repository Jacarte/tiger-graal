package language;

import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.FrameDescriptor;
import com.oracle.truffle.api.frame.MaterializedFrame;

import java.util.HashMap;
import java.util.Map;

public final class TigerLangContext {

    private final FrameDescriptor globalFrameDescriptor;
    private final MaterializedFrame globalFrame;


    public TigerLangContext() {
        this.globalFrameDescriptor = new FrameDescriptor();
        this.globalFrame = Truffle.getRuntime().createVirtualFrame(new Object[]{null}, this.globalFrameDescriptor).materialize();

    }


    public Frame getGlobalFrame(){
        return globalFrame;
    }


    public class BuiltIntDefinition{

        Map<String, Object> def = new HashMap<>();

        public Object getValue(String name) {
            if(def.containsKey(name))
                return def.get(name);

            return null;
        }
    }

}