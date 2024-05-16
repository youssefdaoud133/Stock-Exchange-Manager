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

        closingPrice.setText(String.valueOf(historyPrices.peek()));
        maximumPrice.setText(String.valueOf(findMaxPrice(historyPrices)));
        minimumPrice.setText(String.valueOf(findMinPrice(historyPrices)));
        openingPrice.setText(String.valueOf(historyPrices.get(0)));
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

    private float findMinPrice(Stack<Float> historyPrices) {
        // Find the minimum price
        float minPrice = Float.MAX_VALUE;
        for (Float price : historyPrices) {
            if (price < minPrice) {
                minPrice = price;
            }
        }
        return minPrice; // Minimum price
    }

    private float findMaxPrice(Stack<Float> historyPrices) {
        // Find the maximum price
        float maxPrice = Float.MIN_VALUE;
        for (Float price : historyPrices) {
            if (price > maxPrice) {
                maxPrice = price;
            }
        }
        return maxPrice; // Maximum price
    }

}
