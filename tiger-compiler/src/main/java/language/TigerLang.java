package language;

import com.oracle.truffle.api.CallTarget;
import com.oracle.truffle.api.RootCallTarget;
import com.oracle.truffle.api.Truffle;
import com.oracle.truffle.api.TruffleLanguage;
import com.oracle.truffle.api.debug.DebuggerTags;
import com.oracle.truffle.api.instrumentation.ProvidedTags;
import com.oracle.truffle.api.instrumentation.StandardTags;
import com.oracle.truffle.api.source.Source;
import language.nodes.TigerRootNode;
import language.nodes.antlr.ast.ExpressionNode;
import language.nodes.antlr.ast.MainNode;
import language.nodes.antlr.visitor.TigerAstVisitor;
import language.services.LookupProviderFactory;
import org.antlr.v4.runtime.tree.ParseTree;
import sun.jvm.hotspot.opto.RootNode;

import java.io.InputStream;

@TruffleLanguage.Registration(
        id = TigerLang.ID,
        name="tiger",
        version = "0.1",
        mimeType = TigerLang.MIME_TYPE

)
public final class TigerLang extends TruffleLanguage<TigerLangContext> {

    public static final String MIME_TYPE = "application/x-tiger";

    public static final String ID = "tiger";

    public TigerLang(){

    }

    @Override
    protected TigerLangContext createContext(Env env) {
        return new TigerLangContext();
    }

    @Override
    protected boolean isObjectOfLanguage(Object object) {
        return false;
    }


    private ExpressionNode parseSource(Source source) throws Exception {
        // get user input
        //
        InputStream inputStream = source.getInputStream();

        ParseTree p = TigerAstVisitor.createParseTree(inputStream);

        ExpressionNode pr = TigerAstVisitor.read(this, p,
                new LookupProviderFactory().getProvider(LookupProviderFactory.LOOKUP_TYPE.NAIVE, getContextReference().get().getGlobalFrame()));

        return pr;
    }


    @Override
    protected CallTarget parse(ParsingRequest request) throws Exception {

        ExpressionNode node = parseSource(request.getSource());


        return Truffle.getRuntime().createCallTarget(new TigerRootNode(this, getContextReference().get().getGlobalFrame().getFrameDescriptor(), node));
    }
}
