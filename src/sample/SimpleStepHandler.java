package sample;

import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

import java.util.ArrayList;
import java.util.List;

public class SimpleStepHandler implements StepHandler {
    private List<Point> mPoints;

    public SimpleStepHandler() {
        mPoints = new ArrayList<>();
    }

    @Override
    public void init(double t0, double[] y0, double t) {

    }

    @Override
    public void handleStep(StepInterpolator interpolator, boolean isLast) {
        double   t = interpolator.getCurrentTime();
        double[] y = interpolator.getInterpolatedState();

        mPoints.add(new Point((float)t, (float)y[0]));
    }

    public List<Point> getPoints() {
        return mPoints;
    }
}