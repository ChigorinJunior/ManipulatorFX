package sample;

import org.apache.commons.math3.ode.FirstOrderDifferentialEquations;

public class ManipulatorODE implements FirstOrderDifferentialEquations {
    private static final int DIMENSION = 6;

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

    public ManipulatorODE(SystemParameters systemParameters) {
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

        c1 = J01 + (m2 * r2 * r2) * Math.sin(q2) * Math.sin(q2) + m30*(l2 * Math.sin(q2) + r3 * Math.sin(q3)) * (l2 * Math.sin(q2) + r3 * Math.sin(q3));
        c2 = (m2 * r2 * r2) * Math.sin(q2) * Math.sin(q2) + m30*(l2 * Math.sin(q2) + r3 * Math.sin(q3)) * l2 * Math.sin(q2);
        c3 = m30 * (l2 * Math.sin(q2) + r3 * Math.sin(q3)) * r3 * Math.sin(q3);
        c4 = m2 * r2 * r2 + m30 * l2 * l2;
        c5 = 0.5 * m30 * l2 * r3 * Math.cos(q2 - q3);
        c6 = 0.5 * m30 * l2 * r3 * Math.cos(q2-q3);
        c7 = (m30 * (l2 * Math.sin(q2) + r3 * Math.sin(q3)) + (m2 * r2 * r2) * Math.sin(q2)) * Math.cos(q2);
        c8 = (m2 * r2 + m30 * l2) * g * Math.sin(q2);
        c9 = m30 * r3 * r3;
        c10 = m30 * g * r3 * Math.sin(q3);

        double U1 = -mu1 * Math.sin(q1);
        double U2 = -mu2 * Math.sin(q2);
        double U3 = -mu3 * Math.sin(q3);

        double x1 = U2-c6*dq3*dq3+c7*dq1*dq1-c8-k2*dq2;
        double x2 = U3+c6*dq2*dq2+c3*dq1*dq1-c10-k3*dq3;

        yDot[0] = dq1;
        yDot[1] = (U1 - 2 * c2 * dq1 * dq2 - 2 * c3 * dq1 * dq3 - k1 * dq1) / c1;
        yDot[2] = dq2;
        yDot[3] = (c5 * x2 - c9 * x1) / (c5 * c5 - c4 * c9);
        yDot[4] = dq3;
        yDot[5] = (c4 * x2 - c5 * x1) / (c4 * c9 - c5 * c5);
    }
}
