package ru.ulsu.ibitu.three_chain_manipulator.domain;

public class Time {
    private double time;

    public Time(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Time time1 = (Time) o;

        return Double.compare(time1.time, time) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(time);
        return (int) (temp ^ (temp >>> 32));
    }
}
