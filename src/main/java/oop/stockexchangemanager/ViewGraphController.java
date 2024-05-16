package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Stack;

public class ViewGraphController implements Initializable {

    @FXML
    private LineChart<Number, Number> chart; // Define the chart to accept Number types
    @FXML
    private Label closingPrice;

    @FXML
    private Label maximumPrice;

    @FXML
    private Label minimumPrice;

    @FXML
    private Label openingPrice;
    
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

    private int findAverageIndex(Stack<Float> historyPrices, Float averagePrice) {
        // Assuming averagePrice is the actual average of the prices
        float sum = 0;
        for (Float price : historyPrices) {
            sum += price;
        }
        float actualAverage = sum / historyPrices.size();

        // Find the price closest to the average price
        int index = 0;
        float minDifference = Float.MAX_VALUE;
        for (int i = 0; i < historyPrices.size(); i++) {
            float difference = Math.abs(historyPrices.get(i) - actualAverage);
            if (difference < minDifference) {
                minDifference = difference;
                index = i;
            }
        }
        return index; // Index of the price closest to the average
    }

    private int findMinIndex(Stack<Float> historyPrices) {
        // Find the index of the minimum price
        int index = 0;
        float minPrice = Float.MAX_VALUE;
        for (int i = 0; i < historyPrices.size(); i++) {
            if (historyPrices.get(i) < minPrice) {
                minPrice = historyPrices.get(i);
                index = i;
            }
        }
        return index; // Index of the minimum price
    }

    private int findMaxIndex(Stack<Float> historyPrices) {
        // Find the index of the maximum price
        int index = 0;
        float maxPrice = Float.MIN_VALUE;
        for (int i = 0; i < historyPrices.size(); i++) {
            if (historyPrices.get(i) > maxPrice) {
                maxPrice = historyPrices.get(i);
                index = i;
            }
        }
        return index; // Index of the maximum price
    }

}
