package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Bank.BuyOperation;
import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.Database.Users;
import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.StockPackage.UserStock;
import oop.stockexchangemanager.Utils.AlterOperation;

public class UserCardController {

    @FXML
    private Button BuyButton;

    @FXML
    private Label companyName;

    @FXML
    private Label price;

    @FXML
    private Spinner<Integer> quantitySelector;

    private Stock stock;
    private UserStock userStock;
    private User user;

    private UserPageController userPageController;



    private  SpinnerValueFactory<Integer> spinnerValueFactory;

    public  void initialize(){
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10);
        spinnerValueFactory.setValue(1);
        quantitySelector.setValueFactory(spinnerValueFactory);
    }
    public void setUserPageController(UserPageController userPageController) {
        this.userPageController = userPageController;
    }

    public void setData(UserStock userStock, User user){
        this.stock= Stocks.getInstance().read(userStock.getStockId());
        this.user=user;
        this.userStock = userStock;

        companyName.setText(stock.getCompanyName());
       price.setText(""+userStock.getUserPrice());


    }
    public void buyOperation(){
        try {
            System.out.println(userStock.getUserId());
            BuyOperation.getInstance().buyFromUser(quantitySelector.getValue(),user, Users.getInstance().read(userStock.getUserId()) , userStock);
            userPageController.updatePrice();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlterOperation.showErrorAlert(e.getMessage());
        }
    }


}

