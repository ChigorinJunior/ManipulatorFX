package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import org.codehaus.groovy.runtime.ArrayUtil;
import sample.integrators.SimpsonIntegrator;

import java.util.ArrayList;
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

    @FXML
    TextField textField1;

    @FXML
    TextField textField2;

    @FXML
    TextField textField3;

    @FXML
    TextField textField4;

    @FXML
    TextField textField5;

    @FXML
    TextField textField6;

    @FXML
    TextField textField7;

    @FXML
    TextField textField8;

    @FXML
    TextField textField9;

    @FXML
    TextField textField10;

    @FXML
    TextField textField11;

    @FXML
    TextField textField12;

    @FXML
    TextField textField13;

    @FXML
    TextField textField14;

    @FXML
    TextField textField15;

    @FXML
    TextField textField16;

    @FXML
    Slider timeSlider;

    public void setDefaults(ActionEvent actionEvent) {
        setInitialConditions();
        setInitialControlFunctions();
    }

    public void drawChart(ActionEvent actionEvent) {
        SystemParameters systemParameters = getInitialConditions();
        ControlFunction[] controlFunctions = getControlFunctions();

        Integrator integrator = new Integrator();
        SeriesContainer seriesContainer = integrator.integrate(systemParameters, controlFunctions);

        chart1.getData().clear();
        chart2.getData().clear();
        chart3.getData().clear();
        chart4.getData().clear();
        chart5.getData().clear();
        chart6.getData().clear();

        chart1.getData().add(getSeriesByIndex(0, seriesContainer));
        chart2.getData().add(getSeriesByIndex(1, seriesContainer));
        chart3.getData().add(getSeriesByIndex(2, seriesContainer));
        chart4.getData().add(getSeriesByIndex(3, seriesContainer));
        chart5.getData().add(getSeriesByIndex(4, seriesContainer));
        chart6.getData().add(getSeriesByIndex(5, seriesContainer));

        checkSimpson();
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

    private SystemParameters getInitialConditions() {
        final double J10 = Double.parseDouble(textField1.getText());
        final double m2 = Double.parseDouble(textField2.getText());
        final double m30 = Double.parseDouble(textField3.getText());
        final double l2 = Double.parseDouble(textField4.getText());
        final double r2 = Double.parseDouble(textField5.getText());
        final double r3 = Double.parseDouble(textField6.getText());
        final double k1 = Double.parseDouble(textField7.getText());
        final double k2 = Double.parseDouble(textField8.getText());
        final double k3 = Double.parseDouble(textField9.getText());
        final double mu1 = Double.parseDouble(textField10.getText());
        final double mu2 = Double.parseDouble(textField11.getText());
        final double mu3 = Double.parseDouble(textField12.getText());
        final double g = Double.parseDouble(textField13.getText());

        final double t = timeSlider.getValue();

        double[] parameters = new double[SystemParameters.PARAMETERS_COUNT];

        parameters[0] = J10;
        parameters[1] = m2;
        parameters[2] = m30;
        parameters[3] = l2;
        parameters[4] = r2;
        parameters[5] = r3;
        parameters[6] = k1;
        parameters[7] = k2;
        parameters[8] = k3;
        parameters[9] = mu1;
        parameters[10] = mu2;
        parameters[11] = mu3;
        parameters[12] = g;
        parameters[13] = t;

        return new SystemParameters(parameters);
    }

    private ControlFunction[] getControlFunctions() {
        ControlFunction[] controlFunctions = new ControlFunction[3];

        final String U1 = textField14.getText();
        final String U2 = textField15.getText();
        final String U3 = textField16.getText();

        controlFunctions[0] = new ControlFunction(U1);
        controlFunctions[1] = new ControlFunction(U2);
        controlFunctions[2] = new ControlFunction(U3);

        return controlFunctions;
    }

    private void setInitialConditions() {
        double[] initialConditions = new SystemParameters().getParameters();

        textField1.setText(String.valueOf(initialConditions[0]));
        textField2.setText(String.valueOf(initialConditions[1]));
        textField3.setText(String.valueOf(initialConditions[2]));
        textField4.setText(String.valueOf(initialConditions[3]));
        textField5.setText(String.valueOf(initialConditions[4]));
        textField6.setText(String.valueOf(initialConditions[5]));
        textField7.setText(String.valueOf(initialConditions[6]));
        textField8.setText(String.valueOf(initialConditions[7]));
        textField9.setText(String.valueOf(initialConditions[8]));
        textField10.setText(String.valueOf(initialConditions[9]));
        textField11.setText(String.valueOf(initialConditions[10]));
        textField12.setText(String.valueOf(initialConditions[11]));
        textField13.setText(String.valueOf(initialConditions[12]));

        timeSlider.setValue(initialConditions[13]);
    }

    private void setInitialControlFunctions() {
        ControlFunction[] controlFunctions = ControlFunction.initialControlFunctions;

        textField14.setText(String.valueOf(controlFunctions[0]));
        textField15.setText(String.valueOf(controlFunctions[1]));
        textField16.setText(String.valueOf(controlFunctions[2]));
    }

    //TODO: for debug only
    private void checkSimpson() {
        double t0 = 0.0;
        double t_max = 5.0;

        double current = t0;

        double h = 0.0001;

        List<Double> t = new ArrayList<>();
        List<Double> value = new ArrayList<>();

        while (current < t_max) {
            t.add(current);
            value.add(Math.sin(current));
            current += h;
        }

        double[] t_array = new double[t.size()];
        double[] v_array = new double[value.size()];

        for (int i = 0; i < t_array.length; i++) {
            t_array[i] = t.get(i);
            v_array[i] = value.get(i);
        }

        System.out.print("Simpson ");
        System.out.print(new SimpsonIntegrator().integrate(t_array, v_array));
    }
}
