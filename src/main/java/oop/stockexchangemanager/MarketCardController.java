package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Bank.BuyOperation;
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
    private User user;
    public void setData(Stock stock,User user){
        this.stock=stock;
        this.user=user;
        companyName.setText(stock.getCompanyName());
       price.setText(""+stock.getPrice());

    }
    public void buyOperation(){
        try {
            BuyOperation.getInstance().doOperation(user.getBankAccount(),5,stock);
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText(null);
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
    }
}

