package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.List;

public class Controller {
    @FXML
    LineChart<Number, Number> chart;

    public void drawChart(ActionEvent actionEvent) {
        XYChart.Series series = new XYChart.Series();

        Integrator integrator = new Integrator();
        List<Point> points = integrator.integrate();

        for(Point point: points) {
            series.getData().add(new XYChart.Data(point.getX(), point.getY()));
        }
        chart.getData().add(series);
    }
}
