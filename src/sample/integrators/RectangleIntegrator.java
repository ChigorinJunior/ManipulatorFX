package sample.integrators;

import net.objecthunter.exp4j.Expression;
import sample.Evaluator;

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

    private Expression mExpression;

    private HashMap<String, Double> mMap;

    public RectangleIntegrator(String formula, double h, HashMap<String, Double> map) {
        mFormula = formula;

        mMap = new HashMap<>(map);

        mExpression = Evaluator.buildExpression(mFormula, mMap);

        double t = -h;
        double step = 0.001;

        while(t <= 0.0) {
            mTimes.add(t);
            mMap.put("x", t);
            mValues.add(f(mMap));
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

    public double makeStep(double t, HashMap<String, Double> map) {
        mRightIndex++;

        mMap = new HashMap<>(map);
        mMap.put("x", t);

        mTimes.add(t);
        mValues.add(f(mMap));

        sum = (sum +
                (mValues.get(mRightIndex) + mValues.get(mRightIndex - 1)) / 2 * (mTimes.get(mRightIndex) - mTimes.get(mRightIndex - 1)) -
                (mValues.get(mLeftIndex) + mValues.get(mLeftIndex - 1)) / 2 * (mTimes.get(mLeftIndex) - mTimes.get(mLeftIndex - 1)));

        mLeftIndex++;

        return sum;
    }

    double f(HashMap<String, Double> map) {
        return Evaluator.evaluateExpression(mExpression, map);
    }
}
