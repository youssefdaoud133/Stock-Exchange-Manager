package oop.stockexchangemanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Bank.Deposit;
import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.StockPackage.UserStock;
import oop.stockexchangemanager.Utils.AlterOperation;

public class DepositController {
    @FXML
    private TextField depositField;

    @FXML
    private Button submitButton;
    private   User user ;

    public void setData( User user){
        this.user=user;
    }

    public void submitButtonAction(ActionEvent actionEvent) {
        try {
            // Parse the input from depositField to a float
            String inputText = depositField.getText();
            float depositAmount = Float.parseFloat(inputText);



            // Perform the deposit operation
            Deposit.getInstance().DoOperation(user.getBankAccount(), depositAmount);

            // Clear the input field
            depositField.clear();

            //

        }catch (Exception e){
            AlterOperation.showErrorAlert(e.getMessage());

        }


    }
}
