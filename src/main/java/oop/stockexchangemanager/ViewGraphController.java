package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.ResourceBundle;

public class ViewGraphController implements Initializable {

    @FXML
    private LineChart<Number, Number> chart; // Define the chart to accept Number types

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic if needed
    }

    public void setHistoryPrices(
            Float openingPrice,
            Float closingPrice,
            Float averagePrice,
            Float minimumPrice,
            Float maximumPrice
    ) {
        // Clear any previous data
        chart.getData().clear();

        // Create series for each metric
        XYChart.Series<Number, Number> openingSeries = new XYChart.Series<>();
        openingSeries.setName("Opening Price");

        XYChart.Series<Number, Number> closingSeries = new XYChart.Series<>();
        closingSeries.setName("Closing Price");

        XYChart.Series<Number, Number> averageSeries = new XYChart.Series<>();
        averageSeries.setName("Average Price");

        XYChart.Series<Number, Number> minimumSeries = new XYChart.Series<>();
        minimumSeries.setName("Minimum Price");

        XYChart.Series<Number, Number> maximumSeries = new XYChart.Series<>();
        maximumSeries.setName("Maximum Price");

        // Populate the series with data points
        openingSeries.getData().add(new XYChart.Data<>(0, openingPrice));
        closingSeries.getData().add(new XYChart.Data<>(0, closingPrice));
        averageSeries.getData().add(new XYChart.Data<>(0, averagePrice));
        minimumSeries.getData().add(new XYChart.Data<>(0, minimumPrice));
        maximumSeries.getData().add(new XYChart.Data<>(0, maximumPrice));

        // Add all series to the chart
        chart.getData().addAll(openingSeries, closingSeries, averageSeries, minimumSeries, maximumSeries);
    }
}
