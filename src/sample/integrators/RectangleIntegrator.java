package sample.integrators;

import sample.evaluators.ExpressionEvaluator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RectangleIntegrator {
    private String mFormula;
    private double sum = 0;

    private List<Double> mValues = new ArrayList<>();
    private List<Double> mTimes = new ArrayList<>();

    private int mLeftIndex;
    private int mRightIndex;

    public RectangleIntegrator(String formula, HashMap<String, Double> map, double h) {
        mFormula = formula;
//        mDiffId = diffId;
        mFormula = formula;

        double t = -h;
        double step = 0.001;

        while(t <= 0.0) {
            mTimes.add(t);
            map.put("x", t);
            double v = func(t, 0, map.get("q1"));
            mValues.add(ExpressionEvaluator.evaluateExpression(mFormula, map));
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

    public double makeStep(HashMap<String, Double> map) {
        mRightIndex++;

        double time = map.get("t");
        mTimes.add(time);
        map.put("x", 2 * time);

        double val1 = func(time, 0, map.get("q1"));
        double val2 = ExpressionEvaluator.evaluateExpression(mFormula, map);

        mValues.add(val2);

//        mValues.add(func(t, 0, y[mDiffId]));

        sum = (sum +
                (mValues.get(mRightIndex) + mValues.get(mRightIndex - 1)) / 2 * (mTimes.get(mRightIndex) - mTimes.get(mRightIndex - 1)) -
                (mValues.get(mLeftIndex) + mValues.get(mLeftIndex - 1)) / 2 * (mTimes.get(mLeftIndex) - mTimes.get(mLeftIndex - 1)));

        return sum;
    }

    double func(double x, double t, double q) {
        return Math.exp(x - t) * Math.sin(q);
    }
}
