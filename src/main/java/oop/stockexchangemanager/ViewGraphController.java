package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewGraphController implements Initializable {

    @FXML
    private LineChart<?, ?> chart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("1", 20));
        series.getData().add(new XYChart.Data("2", 40));
        series.getData().add(new XYChart.Data("3", 70));
        chart.getData().add(series);
    }
}


