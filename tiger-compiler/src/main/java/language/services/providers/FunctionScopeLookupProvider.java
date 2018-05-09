package language.services.providers;

import javafx.util.Pair;
import language.nodes.antlr.ast.block.functions.ArgDeclaration;
import language.services.IFunctionArgLookupProvider;
import language.services.ILookupProvider;

import java.util.HashMap;
import java.util.Map;

public class FunctionScopeLookupProvider implements IFunctionArgLookupProvider {

    IFunctionArgLookupProvider parent;

    public FunctionScopeLookupProvider(IFunctionArgLookupProvider parent){
        this.parent = parent;
        this.args = new HashMap<>();
    }

    @Override
    public IFunctionArgLookupProvider addChild(IFunctionArgLookupProvider parent) {
        return new FunctionScopeLookupProvider(this);
    }

    @Override
    public IFunctionArgLookupProvider getParent() {
        return parent;
    }

    @Override
    public Pair<ArgDeclaration, Integer> getArg(String name) {

        int depth = 0;
        IFunctionArgLookupProvider parent = this;

        while(true){
            if(parent.getArgs().containsKey(name)){
                return new Pair<>(parent.getArgs().get(name), depth);
            }

            parent = parent.getParent();

            depth++;
            if(parent == null)
                break;
        }

        return null;
    }

    @Override
    public void addArgNode(String name, ArgDeclaration arg) {
        args.put(name, arg);
    }

    @Override
    public Map<String, ArgDeclaration> getArgs() {
        return args;
    }


    Map<String, ArgDeclaration> args;
}
