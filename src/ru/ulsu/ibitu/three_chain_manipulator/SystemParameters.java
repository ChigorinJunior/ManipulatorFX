package ru.ulsu.ibitu.three_chain_manipulator;

public class SystemParameters {
    // system parameters count + time
    public static final int PARAMETERS_COUNT = 13 + 1;

    double J01 = 0.1;
    double m2 = 15;
    double m30 = 2.5;
    double l2 = 1;
    double r2 = 0.5;
    double r3 = 0.5;
    double k1 = 0.12;
    double k2 = 0.12;
    double k3 = 0.12;
    double mu1 = 1;
    double mu2 = 1;
    double mu3 = 1;
    double g = 9.81;

    double t = 30.0;

    public SystemParameters() {}

    public SystemParameters(double[] parameters) {
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
    }

    public double[] getParameters() {
        double[] defaults = new double[PARAMETERS_COUNT];

        defaults[0] = J01;
        defaults[1] = m2;
        defaults[2] = m30;
        defaults[3] = l2;
        defaults[4] = r2;
        defaults[5] = r3;
        defaults[6] = k1;
        defaults[7] = k2;
        defaults[8] = k3;
        defaults[9] = mu1;
        defaults[10] = mu2;
        defaults[11] = mu3;
        defaults[12] = g;

        defaults[13] = t;

        return defaults;
    }

    public double getTime() {
        return t;
    }
}
