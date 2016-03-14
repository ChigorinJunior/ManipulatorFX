package sample;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.stage.Stage;

public class Main extends Application {
    @FXML
    LineChart chart;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Integrator integrator = new Integrator();
//
//        List<Point> points = integrator.integrate();
//
//        PlotDrawer plotDrawer = new PlotDrawer(primaryStage);
//        plotDrawer.draw(points);
//
//        GridPane pane = new GridPane();
//        pane.setAlignment(Pos.CENTER);
//
//        pane.setHgap(10);
//        pane.setVgap(10);
//
//        Scene scene = new Scene(pane, 300, 275);
//
//        pane.add(plotDrawer.draw(points));
//
//        primaryStage.setScene(scene);
//        primaryStage.show();

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
