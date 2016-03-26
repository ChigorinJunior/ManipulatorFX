package sample;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import org.apache.commons.math3.ode.FirstOrderIntegrator;
import org.apache.commons.math3.ode.nonstiff.DormandPrince853Integrator;

public class Integrator {
    private SimpleStepHandler mStepHandler;

    public Integrator() {
        mStepHandler = new SimpleStepHandler();
    }

    public SeriesContainer integrate(SystemParameters systemParameters, ControlFunction... controlFunctions) {
        getIntegrator(systemParameters, controlFunctions);
        return mStepHandler.getSeriesContainer();
    }

    public FirstOrderIntegrator getIntegrator(SystemParameters systemParameters, ControlFunction... controlFunctions) {
       FirstOrderIntegrator dp853 = new DormandPrince853Integrator(1.0e-6, 100.0, 1.0e-4, 1.0e-4);
        FirstOrderDifferentialEquations ode = new ManipulatorODE(systemParameters, controlFunctions);
        double[] y = new double[] {0.2, 0, 0.2, 0, 0.2, 0}; // initial state
        dp853.addStepHandler(mStepHandler);
        dp853.integrate(ode, 0.0, y, systemParameters.getTime(), y);
        return dp853;
    }
}