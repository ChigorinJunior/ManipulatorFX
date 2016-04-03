package ru.ulsu.ibitu.three_chain_manipulator;

import java.util.ArrayList;
import java.util.List;

public class SeriesContainer {
    private List<List<Point>> mPoints;

    public SeriesContainer(int size) {
        mPoints = new ArrayList<>(size);

        for (int i= 0; i < size; i++) {
            mPoints.add(new ArrayList<>());
        }
    }

    public List<Point> getSeries(int index) {
        return mPoints == null? null : mPoints.get(index);
    }
}
