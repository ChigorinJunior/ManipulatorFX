package sample;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;

import java.util.List;

public class Integrator {
    private static final double TIME = 30.0;

    private SimpleStepHandler mStepHandler;

    public Integrator() {
        mStepHandler = new SimpleStepHandler();
    }

    public List<Point> integrate() {
        getIntegrator();
        return mStepHandler.getPoints();
    }

    public FirstOrderIntegrator getIntegrator() {
        FirstOrderIntegrator dp853 = new DormandPrince853Integrator(1.0e-8, 100.0, 1.0e-10, 1.0e-10);
        FirstOrderDifferentialEquations ode = new ManipulatorODE();
        double[] y = new double[] {0.2, 0, 0.2, 0, 0.2, 0}; // initial state
        dp853.addStepHandler(mStepHandler);
        dp853.integrate(ode, 0.0, y, TIME, y);
        return dp853;
    }
}