package sample.integrators;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.sampling.StepHandler;

public class RungeKuttaIntegrator {
    private static final double STEP_SIZE = 0.1;

    private double t = 0.0;
    private double[] vector = new double[6];

    private StepHandler mStepHandler;

    public StepHandler getStepHandler() {
        return mStepHandler;
    }

    public void setStepHandler(StepHandler stepHandler) {
        this.mStepHandler = stepHandler;
    }

    public void integrate(FirstOrderDifferentialEquations ode, double startTime, double[] vector, double endTime) {
        double[] out = new double[ode.getDimension()];
        this.vector = vector;

        t = startTime;

        while (t < endTime) {

        }

        ode.computeDerivatives(this.t, vector, out);
    }

    private void makeStep(double[] vector) {
//        double[] k1 = 1
    }
}
