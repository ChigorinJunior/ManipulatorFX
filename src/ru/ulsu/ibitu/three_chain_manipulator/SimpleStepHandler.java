package ru.ulsu.ibitu.three_chain_manipulator;

import org.apache.commons.math3.ode.sampling.StepHandler;
import org.apache.commons.math3.ode.sampling.StepInterpolator;

public class SimpleStepHandler implements StepHandler {
    public static final int SERIES_COUNT = 6;

    private SeriesContainer mSeriesContainer;

    public SimpleStepHandler() {
        mSeriesContainer = new SeriesContainer(SERIES_COUNT);
    }

    @Override
    public void init(double t0, double[] y0, double t) {

    }

    @Override
    public void handleStep(StepInterpolator interpolator, boolean isLast) {
        double   t = interpolator.getCurrentTime();
        double[] y = interpolator.getInterpolatedState();

        for (int i = 0; i < SERIES_COUNT; i++) {
            mSeriesContainer.getSeries(i).add(new Point((float) t, (float) y[i]));
        }
    }

    public SeriesContainer getSeriesContainer() {
        return mSeriesContainer;
    }
}