package language.services;

import javafx.util.Pair;
import language.nodes.antlr.ast.block.functions.ArgDeclaration;

import java.util.Map;

public interface IFunctionArgLookupProvider {

    IFunctionArgLookupProvider addChild(IFunctionArgLookupProvider parent);

    IFunctionArgLookupProvider getParent();

    Pair<ArgDeclaration, Integer> getArg(String name);

    void addArgNode(String name, ArgDeclaration arg);

    Map<String, ArgDeclaration> getArgs();
}
