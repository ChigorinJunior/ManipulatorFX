package ru.ulsu.ibitu.three_chain_manipulator.functions;

import net.objecthunter.exp4j.function.Function;

public class CustomFunctionsFactory {

    public Function[] getCustomFunctions() {
        Function[] functions = new Function[2];
        functions[0] = Signum.getFunction();
        functions[1] = Integral.getFunction();

        return functions;
    }
}
