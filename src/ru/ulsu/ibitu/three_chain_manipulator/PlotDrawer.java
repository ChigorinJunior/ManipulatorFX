package ru.ulsu.ibitu.three_chain_manipulator;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.List;

public class PlotDrawer {
    private Stage mStage;

    public PlotDrawer(Stage stage) {
        mStage = stage;
    }

    public LineChart<Number, Number>draw(List<Point> points) {
        mStage.setTitle("Трехзвенный манипулятор");
        //defining the axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("t");
        //creating the chart1
        final LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);

        lineChart.setTitle("q1");
        //defining a series
        XYChart.Series series = new XYChart.Series();
        series.setName("123");

        for(Point point: points) {
            series.getData().add(new XYChart.Data(point.getX(), point.getY()));
        }

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);

        mStage.setScene(scene);
        mStage.show();

        return lineChart;
    }
}