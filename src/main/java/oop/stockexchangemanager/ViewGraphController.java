package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class ViewGraphController implements Initializable {

    @FXML
    private LineChart<Number, Number> chart; // Define the chart to accept Number types

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic if needed
    }

    public void setHistoryPrices(Stack<Float> historyPrices) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();

        int index = 0;
        for (Float price : historyPrices) {
            series.getData().add(new XYChart.Data<>(index, price));
            index++;
        }

        // Add series to chart
        chart.getData().add(series);
    }
}
