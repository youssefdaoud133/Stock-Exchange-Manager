package oop.stockexchangemanager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import oop.stockexchangemanager.AccountPackage.Account;
import oop.stockexchangemanager.AccountPackage.Authentication;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.AccountPage.AccountPage;
import oop.stockexchangemanager.AccountPage.AccountPageFactory;
import oop.stockexchangemanager.RTK.Rtk;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HelloController  {
    public Label ErrorMsg;
    @FXML
    private Button login, newAccount;
    @FXML
    private TextField email;
    @FXML
    private PasswordField Password;
    @FXML
    private static Stage loginstage;

    @FXML
    private static Stage signupStage; // Singleton instance



    @FXML
    public void login() throws IOException {
        try {
            String Email = email.getText();
            String password = Password.getText();
            Account account;
            account = Authentication.signInAsAdmin(Email, password);

             if(account == null) {
                 account = Authentication.signInAsUser(Email, password);
             }
            AccountPage page = AccountPageFactory.getPage(account);
            page.showPage(); // This will show the correct page based on the account type


        } catch (Exception e) {
            ErrorMsg.setText(e.getMessage());
            System.out.println(e.getMessage());
        }
    }


    @FXML
    public void newAccount() throws IOException {
        try {
            if (signupStage == null) {
                // If the stage doesn't exist, create it
                signupStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("newAccount.fxml"));
                signupStage.setScene(new Scene(root));
                signupStage.setTitle("StockExchangeManager");
                signupStage.setOnCloseRequest(event -> {
                    // Reset the stage reference when closed
                    signupStage = null;
                });
            }
            SignupController.setStage(signupStage);
            signupStage.show(); // Show the stage
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }








}
