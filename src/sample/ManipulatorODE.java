package sample;

import net.objecthunter.exp4j.Expression;
import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;
import sample.domain.DiffValues;
import sample.domain.Time;
import sample.integrators.RectangleIntegrator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ManipulatorODE implements FirstOrderDifferentialEquations {
    public static ManipulatorODE sInstance;

    private static final int DIMENSION = 6;

    private Map<Time, DiffValues> mHistoryMap;

    double J01 = 0.1;
    double m2 = 15;
    double m30 = 2.5;
    double l2 = 1;
    double r2 = 0.5;
    double r3 = 0.5;
    double k1 = 0.12;
    double k2 = 0.12;
    double k3 = 0.12;
    double mu1 = -50;
    double mu2 = -50;
    double mu3 = 10;
    double g = 9.81;

    double t = 30.0;

    double mCurrentTime = 0.0;
    HashMap<String, Double> map;

    RectangleIntegrator mRectangleIntegrator1;
    RectangleIntegrator mRectangleIntegrator2;
    RectangleIntegrator mRectangleIntegrator3;

    String U1;
    String U2;
    String U3;

    String mIntU1;
    String mIntU2;
    String mIntU3;

    Expression mExpressionU1;
    Expression mExpressionU2;
    Expression mExpressionU3;

    public ManipulatorODE(SystemParameters systemParameters, ControlFunction... controlFunctions) {
        double[] parameters = systemParameters.getParameters();

        J01 = parameters[0];
        m2 = parameters[1];
        m30 = parameters[2];
        l2 = parameters[3];
        r2 = parameters[4];
        r3 = parameters[5];
        k1 = parameters[6];
        k2 = parameters[7];
        k3 = parameters[8];
        mu1 = parameters[9];
        mu2 = parameters[10];
        mu3 = parameters[11];
        g = parameters[12];

        t = parameters[13];

        U1 = replaceIntegrals(controlFunctions[0].getFunction(), 1);
        U2 = replaceIntegrals(controlFunctions[1].getFunction(), 2);
        U3 = replaceIntegrals(controlFunctions[2].getFunction(), 3);

        mIntU1 = getIntegralFunction(controlFunctions[0].getFunction());
        mIntU2 = getIntegralFunction(controlFunctions[1].getFunction());
        mIntU3 = getIntegralFunction(controlFunctions[2].getFunction());

        mHistoryMap = new LinkedHashMap<>();

        sInstance = this;
    }

    public int getDimension() {
        return DIMENSION;
    }

    public void computeDerivatives(double t, double[] y, double[] yDot) {
        double c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;
        double q1, dq1, q2, dq2, q3, dq3;

        q1 = y[0];
        dq1 = y[1];

        q2 = y[2];
        dq2 = y[3];

        q3 = y[4];
        dq3 = y[5];

        mHistoryMap.put(new Time(t), new DiffValues(y));

        c1 = J01+(m2*r2*r2)*Math.sin(q2)*Math.sin(q2)+m30*(l2*Math.sin(q2)+r3*Math.sin(q3))*(l2*Math.sin(q2)+r3*Math.sin(q3));
        c2 = (m2*r2*r2)*Math.sin(q2)*Math.cos(q2)+m30*(l2*Math.sin(q2)+r3*Math.sin(q3))*l2*Math.cos(q2);
        c3 = m30*(l2*Math.sin(q2)+r3*Math.sin(q3))*r3*Math.cos(q3);
        c4 = m2*r2*r2+m30*l2*l2;
        c5 = .5*m30*l2*r3*Math.cos(q2-q3);
        c6 = .5*m30*l2*r3*Math.sin(q2-q3);
        c7 = (m30*(l2*Math.sin(q2)+r3*Math.sin(q3))+(m2*r2*r2)*Math.sin(q2))*Math.cos(q2);
        c8 = (m2*r2+m30*l2)*g*Math.sin(q2);
        c9 = m30*r3*r3;
        c10 = m30*g*r3*Math.sin(q3);

        map = constructMap(t, y);

        mCurrentTime = t;

        if (mExpressionU1 == null) {
            mExpressionU1 = Evaluator.buildExpression(U1, map);
            mExpressionU2 = Evaluator.buildExpression(U2, map);
            mExpressionU3 = Evaluator.buildExpression(U3, map);
        }

        if (Double.compare(0.0, t) == 0) {
            mRectangleIntegrator1 = new RectangleIntegrator(mIntU1, 1, map);
            mRectangleIntegrator2 = new RectangleIntegrator(mIntU2, 1, map);
            mRectangleIntegrator3 = new RectangleIntegrator(mIntU3, 1, map);
        }

        double U1 = calcU1(t, y, map);
        double U2 = calcU2(t, y, map);
        double U3 = calcU3(t, y, map);

        double x1 = U2-c6*dq3*dq3+c7*dq1*dq1-c8-k2*dq2;
        double x2 = U3+c6*dq2*dq2+c3*dq1*dq1-c10-k3*dq3;

        yDot[0] = dq1;
        yDot[1] = (U1 - 2 * c2 * dq1 * dq2 - 2 * c3 * dq1 * dq3 - k1 * dq1) / (c1 + 0.001);
        yDot[2] = dq2;
        yDot[3] = (c5 * x2 - c9 * x1) / ( (c5 * c5 - c4 * c9) + 0.01);
        yDot[4] = dq3;
        yDot[5] = (c4 * x2 - c5 * x1) / ( (c4 * c9 - c5 * c5) + 0.01);

        System.out.println(t);
    }

    private HashMap<String, Double> constructMap(double t, double[] y) {
        HashMap<String, Double> map = new HashMap<>();
        double q1, dq1, q2, dq2, q3, dq3;

        q1 = y[0];
        dq1 = y[1];

        q2 = y[2];
        dq2 = y[3];

        q3 = y[4];
        dq3 = y[5];

        map.put("q1", q1);
        map.put("dq1", dq1);
        map.put("q2", q2);
        map.put("dq2", dq2);
        map.put("q3", q3);
        map.put("dq3", dq3);
        map.put("t", t);

        map.put("J01", this.J01);
        map.put("m2", this.m2);
        map.put("m30", this.m30);
        map.put("l2", this.l2);
        map.put("r2", this.r2);
        map.put("r3", this.r3);

        map.put("k1", this.k1);
        map.put("k2", this.k2);
        map.put("k3", this.k3);

        map.put("mu1", this.mu1);
        map.put("mu2", this.mu2);
        map.put("mu3", this.mu3);

        map.put("g", this.g);

        map.put("x", 0.0);

        return map;
    }

    private double calcU1(double t, double[]y, HashMap<String, Double> map) {
        return Evaluator.evaluateExpression(mExpressionU1, map);
    }

    private double calcU2(double t, double[]y, HashMap<String, Double> map) {
        return Evaluator.evaluateExpression(mExpressionU2, map);
    }

    private double calcU3(double t, double[]y, HashMap<String, Double> map) {
        return Evaluator.evaluateExpression(mExpressionU3, map);
    }

    private String getIntegralFunction(String formula) {
        if (formula.contains("Int")) {
            int startPosition = formula.indexOf('<');
            int endPosition = formula.indexOf('>');

            String[] parts = formula.substring(startPosition, endPosition).split(",");

            return parts[parts.length - 1].trim();
        }

        return "";
    }

    private String replaceIntegrals(String formula, int index) {
        return formula.replaceAll("<.*?>", "(" + String.valueOf(index) + ")");
    }

    public double makeStep(int index) {
        switch (index) {
            case 1:
                return mRectangleIntegrator1.makeStep(mCurrentTime, map);
            case 2:
                return mRectangleIntegrator2.makeStep(mCurrentTime, map);
            case 3:
                return mRectangleIntegrator3.makeStep(mCurrentTime, map);
        }

        return 0.0;
    }
}
