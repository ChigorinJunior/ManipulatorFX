package ru.ulsu.ibitu.three_chain_manipulator.test;

public class TestFunctions {
    private double alph;

    private double mu1;
    private double mu2;
    private double mu3;
    private double mu4;

    double testU1(double t, double[] y) {
        return mu1 * (Math.sin(y[0] - q10(t)) + alph*(y[1] - diff_q10(t)));
    }

    double testU2(double t, double[] y) {
        return mu2 * (Math.sin(y[2] - q20(t)) + alph * (y[3] - (diff_q20(t)))) + mu3 * (y[4] - q30(t) + alph * (y[5] - (diff_q30(t))));
    }

    double testU3(double t, double[] y) {
        return mu3*(Math.sin(y[2] - q20(t)) + alph * (y[3] - (diff_q20(t)))) + mu4 * (y[4] - q30(t) + alph * (y[5] - diff_q30(t)));
    }

    double q10(double t) {
        return  0.2 * t;
    }

    double q20(double t) {
        return 1.5 + 0.5 * Math.sin(t);
    }

    double q30(double t) {
        return 0.5 * Math.sin(0.5 * t);
    }

    double diff_q10(double t) {
        return 0.2;
    }

    double diff_q20(double t) {
        return  0.5 * Math.cos(t);
    }

    double diff_q30(double t) {
        return 0.5 * 0.5 * Math.cos(0.5 * t);
    }
}
