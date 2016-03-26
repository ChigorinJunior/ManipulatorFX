package sample.integrators;

import java.util.ArrayList;
import java.util.List;

public class RectangleIntegrator {
    private String mFormula;
    private double sum = 0;

    private List<Double> mValues = new ArrayList<>();
    private List<Double> mTimes = new ArrayList<>();

    private int mLeftIndex;
    private int mRightIndex;

    private int mDiffId;

    public RectangleIntegrator(String formula, double[] y, double h, int diffId) {
        mDiffId = diffId;
        mFormula = formula;

        double t = -h;
        double step = 0.001;

        while(t <= 0.0) {
            mTimes.add(t);
            mValues.add(f(t, 0, y[mDiffId]));
            t += step;
        }

        sum = integrate();

        mLeftIndex = 1;
        mRightIndex = mValues.size() - 1;
    }

    public double integrate() {
        double sum = 0.0;

        for (int i = 0; i < mTimes.size() - 1; i++) {
            sum += (mValues.get(i) + mValues.get(i+1))/2 * (mTimes.get(i+1) - mTimes.get(i));
        }

        return sum;
    }

    public double integrate(double[] t, double[] y) {
        int size = y.length;

        double sum = 0.0;

        for (int i = 0; i < size - 1; i++) {
            sum += (y[i] + y[i+1])/2 * (t[i+1] - t[i]);
        }

        return sum;
    }

    double f(double x, double t, double q) {
        return Math.exp(x - t) * Math.sin(q);
    }

    public double makeStep(double t, double[] y) {
        mRightIndex++;

        mTimes.add(t);
        mValues.add(y[mDiffId]);

        sum = (sum +
                (mValues.get(mRightIndex) + mValues.get(mRightIndex - 1)) / 2 * (mTimes.get(mRightIndex) - mTimes.get(mRightIndex - 1)) -
                (mValues.get(mLeftIndex) + mValues.get(mLeftIndex - 1)) / 2 * (mTimes.get(mLeftIndex) - mTimes.get(mLeftIndex - 1)));

        return sum;
    }
}
