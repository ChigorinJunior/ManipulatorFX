package sample.functions;

import net.objecthunter.exp4j.function.Function;
import sample.ManipulatorODE;

public class Integral {
    private static final String NAME = "Int";

    public static String getName() {
        return NAME;
    }

    public static Function getFunction() {
        return new Function(NAME, 1) {

            @Override
            public double apply(double... args) {
                int index = (int)args[0];
                return ManipulatorODE.getInstance().calculate(index);
            }
        };
    }
}
