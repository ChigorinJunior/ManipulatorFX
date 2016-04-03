package ru.ulsu.ibitu.three_chain_manipulator.functions;

import net.objecthunter.exp4j.function.Function;

public class Signum {

    public static Function getFunction() {
        return new Function("signum", 1) {

            @Override
            public double apply(double... args) {
                double value = args[0];

                if (value > 0) {
                    return 1;
                } else {
                    if (value < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        };
    }
}
