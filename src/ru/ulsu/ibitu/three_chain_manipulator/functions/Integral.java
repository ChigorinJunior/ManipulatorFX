package ru.ulsu.ibitu.three_chain_manipulator.functions;

import net.objecthunter.exp4j.function.Function;
import ru.ulsu.ibitu.three_chain_manipulator.ManipulatorODE;

public class Integral {

    public static Function getFunction() {
        return new Function("Int", 1) {

            @Override
            public double apply(double... args) {
                int index = (int) args[0];
                return ManipulatorODE.sInstance.makeStep(index);
            }
        };
    }
}
