package sample;

public class ControlFunction {
    public static ControlFunction[] initialControlFunctions = {
            new ControlFunction("- mu1 * sin(q1)"),
            new ControlFunction("- mu2 * sin(q2)"),
            new ControlFunction("- mu3 * sin(q3)")
    };

    private String mFunction;

    public ControlFunction(String mFunction) {
        this.mFunction = mFunction;
    }

    public String getFunction() {
        return mFunction;
    }

    public void setFunction(String function) {
        this.mFunction = function;
    }

    @Override
    public String toString() {
        return mFunction;
    }
}