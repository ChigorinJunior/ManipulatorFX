package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.List;

public class Controller {
    @FXML
    LineChart<Number, Number> chart1;

    @FXML
    LineChart<Number, Number> chart2;

    @FXML
    LineChart<Number, Number> chart3;

    @FXML
    LineChart<Number, Number> chart4;

    @FXML
    LineChart<Number, Number> chart5;

    @FXML
    LineChart<Number, Number> chart6;

    public void drawChart(ActionEvent actionEvent) {
        Integrator integrator = new Integrator();
        SeriesContainer seriesContainer = integrator.integrate();

        chart1.getData().add(getSeriesByIndex(0, seriesContainer));
        chart2.getData().add(getSeriesByIndex(1, seriesContainer));
        chart3.getData().add(getSeriesByIndex(2, seriesContainer));
        chart4.getData().add(getSeriesByIndex(3, seriesContainer));
        chart5.getData().add(getSeriesByIndex(4, seriesContainer));
        chart6.getData().add(getSeriesByIndex(5, seriesContainer));
    }

    @SuppressWarnings("unchecked")
    private XYChart.Series<Number, Number> getSeriesByIndex(int index, SeriesContainer seriesContainer) {
        XYChart.Series series = new XYChart.Series();

        List<Point> points = seriesContainer.getSeries(index);

        for(Point point: points) {
            series.getData().add(new XYChart.Data(point.getX(), point.getY()));
        }

        return series;
    }
}
