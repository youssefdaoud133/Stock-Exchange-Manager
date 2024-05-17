package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Bank.BuyOperation;
import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.Utils.AlterOperation;

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
    private UserPageController userPageController;
    private  SpinnerValueFactory<Integer> spinnerValueFactory;
    public void setUserPageController(UserPageController userPageController) {
        this.userPageController = userPageController;
    }

    public  void initialize(){
        SpinnerValueFactory<Integer> spinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1,10);
        spinnerValueFactory.setValue(1);
        quantitySelector.setValueFactory(spinnerValueFactory);
    }

    public void setData(Stock stock, User user){
        this.stock=stock;
        this.user=user;

        companyName.setText(stock.getCompanyName());
       price.setText(""+stock.getPrice());

    }
    public void buyOperation(){
        try {
            BuyOperation.getInstance().doOperation(user.getBankAccount(), quantitySelector.getValue(), stock);

            userPageController.updatePrice();

        } catch (Exception e) {
            AlterOperation.showErrorAlert(e.getMessage());
        }
    }


}

