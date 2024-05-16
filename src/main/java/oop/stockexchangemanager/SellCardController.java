package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.Database.UserStocks;
import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.StockPackage.UserStock;
import oop.stockexchangemanager.Utils.AlterOperation;

public class SellCardController {
    public Label LivePrice;
    @FXML
    private Label companyName;

    @FXML
    private TextField PriceField;

    @FXML
    private Button sellButton;

    @FXML
    private Label quantityLabel;
    private User user;
    private Stock stock;
    private int quantity;

    public void sellOperation(){
        try{
            UserStock userStock = UserStock.Generate(user.getId() , stock.getId(), Float.parseFloat(PriceField.getText()),quantity);
            UserStocks.getInstance().create(userStock.getId(),userStock);
        }catch (Exception e){
            AlterOperation.showErrorAlert(e.getMessage());
        }


    }
    public void setData( User user ,int id , int quantity ){
        this.user=user;
        Stock stock = Stocks.getInstance().read(id);
        this.stock=stock;
        this.quantity=quantity;
        companyName.setText(stock.getCompanyName());
        LivePrice.setText(""+stock.getPrice());
        quantityLabel.setText(""+quantity);

    }
}
