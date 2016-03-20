package sample.integrators;

public class SimpsonIntegrator {
    public double integrate(double[] t, double[] y) {
        int size = y.length;

        if (size < 3) {
            return 0;
        }

        double sum = 0.0;

        for (int i = 2; i < size - 1; i+=2) {
            double a = t[i-2];
            double b = t[i];

            double left = y[i-2];
            double middle = y[i-2];
            double right = y[i];

            sum += calculateOnLine(a, b, left, middle, right);
        }

        return sum;
    }

    private double calculateOnLine(double a, double b, double left, double middle, double right) {
        return (b - a)/6 * (left + 4 * middle + right);
    }
}
