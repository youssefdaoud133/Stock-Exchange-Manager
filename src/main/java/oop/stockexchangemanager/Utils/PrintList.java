package oop.stockexchangemanager.Utils;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Database.Collections;
import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.MarketCardController;
import oop.stockexchangemanager.SellCardController;
import oop.stockexchangemanager.StockPackage.Stock;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PrintList {
    public static void populateStocksGrid(User user, GridPane grid, ScrollPane scroll, String controllerName) throws IOException {
        List<Stock> stocks = new ArrayList<>();
        stocks.addAll(Stocks.getInstance().readAll());
        int column = 0;
        int row = 1;


            for (int i = 0; i < stocks.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(MarketCardController.class.getResource(controllerName + ".fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                MarketCardController itemController = fxmlLoader.getController();
                itemController.setData(stocks.get(i), user);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); // (child, column, row)
                PrintList.GridOperation(grid,scroll,anchorPane);

            }
        PrintList.ScrollOperation(grid,scroll);




    }

    public static void populateOwnerStocksGrid(User user, GridPane grid, ScrollPane scroll, String controllerName) throws IOException {
//        List<Stock> stocks = new ArrayList<>();
//        stocks.addAll( user.getOwnedStocks().readAll());
        int column = 0;
        int row = 1;



        for (Map.Entry<Integer, Integer> entry : user.getOwnedStocks().getData().entrySet()) {


            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(SellCardController.class.getResource(controllerName + ".fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            SellCardController itemController = fxmlLoader.getController();
            itemController.setData(user,entry.getKey(),entry.getValue());

            if (column == 2) {
                column = 0;
                row++;
            }

            grid.add(anchorPane, column++, row); // (child, column, row)
            PrintList.GridOperation(grid,scroll,anchorPane);

        }
        PrintList.ScrollOperation(grid,scroll);




    }

    public static void GridOperation(GridPane grid, ScrollPane scroll,AnchorPane anchorPane ){
        // Set grid width and height properties
        grid.setMinWidth(Region.USE_COMPUTED_SIZE);
        grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
        grid.setMaxWidth(Region.USE_PREF_SIZE);
        grid.setMinHeight(Region.USE_COMPUTED_SIZE);
        grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
        grid.setMaxHeight(Region.USE_PREF_SIZE);

        grid.setHgap(240); // Horizontal gap
        grid.setVgap(30); // Vertical gap

        GridPane.setMargin(anchorPane, new Insets(10, 60, 10, 20));

    }
    public static void ScrollOperation(GridPane grid, ScrollPane scroll){
        scroll.setVvalue(1.0); // Scrolls to the bottom
        scroll.setContent(grid); // Set the grid as the content of the scroll pane
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

    }


}
