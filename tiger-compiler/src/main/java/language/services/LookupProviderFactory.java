package language.services;

import com.oracle.truffle.api.frame.Frame;
import com.oracle.truffle.api.frame.VirtualFrame;
import language.services.providers.NaiveLookupProvider;

public class LookupProviderFactory {

    public enum LOOKUP_TYPE {
        NAIVE,
        PATH_CACHE,
        CACHE_FRAME
    }


    public ILookupProvider getProvider(LOOKUP_TYPE type, VirtualFrame frame){

        return new NaiveLookupProvider(null, frame);
    }

}
