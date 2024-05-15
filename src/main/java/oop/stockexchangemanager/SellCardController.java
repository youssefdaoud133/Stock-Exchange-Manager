package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.StockPackage.Stock;

public class SellCardController {
    @FXML
    private Label companyName;

    @FXML
    private TextField PriceField;

    @FXML
    private Button sellButton;

    @FXML
    private Label quantityLabel;

    public void sellOperation(){
        System.out.println(PriceField.getText());
    }
    public void setData( User user ,int id , int quantity ){
        companyName.setText(Stocks.getInstance().read(id).getCompanyName());
        quantityLabel.setText(""+quantity);

    }
}
