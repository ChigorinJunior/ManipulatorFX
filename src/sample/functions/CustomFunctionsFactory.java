package sample.functions;

import net.objecthunter.exp4j.function.Function;

public class CustomFunctionsFactory {

    public Function[] getCustomFunctions() {
        Function[] functions = new Function[1];
        functions[0] = Signum.getFunction();

        return functions;
    }
}
