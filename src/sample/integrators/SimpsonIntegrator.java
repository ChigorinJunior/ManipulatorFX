package sample.integrators;

import java.util.List;

public class SimpsonIntegrator {
    public double integrate(double[] t, double[] y) {
        int size = y.length;

        // TODO: fix me
        if (size < 3) {
            return 0;
        }

        double sum = 0.0;

        for (int i = 2; i < size - 1; i+=2) {
            double a = t[i-2];
            double b = t[i];

            double left = y[i-2];
            double middle = y[i-1];
            double right = y[i];

            sum += calculateOnLine(a, b, left, middle, right);
        }

        return sum;
    }

    private double calculateOnLine(double a, double b, double left, double middle, double right) {
        return (b - a)/6 * (left + 4 * middle + right);
    }

    public static double[] fromList(List<Double> values) {
        double[] convertedValues = new double[values.size()];

        for (int i = 0; i < values.size(); i++) {
            convertedValues[i] = values.get(i);
        }

        return convertedValues;
    }
}
