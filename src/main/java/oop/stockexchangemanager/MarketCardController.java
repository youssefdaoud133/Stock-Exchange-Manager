package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import oop.stockexchangemanager.StockPackage.Stock;

public class MarketCardController {

    @FXML
    private Button BuyButton;

    @FXML
    private Label companyName;

    @FXML
    private Label price;

    @FXML
    private Spinner<Integer> quantitySelector;

    private Stock stock;
    public void setData(Stock stock){
        this.stock=stock;
        companyName.setText(stock.getCompanyName());
       price.setText(""+stock.getPrice());

    }




}

