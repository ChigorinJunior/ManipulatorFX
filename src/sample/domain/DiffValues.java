package sample.domain;

import java.util.Arrays;

public class DiffValues {
    double[] values;

    public DiffValues(double[] values) {
        this.values = values;
    }

    public double[] getValues() {
        return values;
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DiffValues that = (DiffValues) o;

        return Arrays.equals(values, that.values);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(values);
    }
}
