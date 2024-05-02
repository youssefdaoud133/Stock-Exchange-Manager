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
import oop.stockexchangemanager.AccountPackage.Authentication;
import oop.stockexchangemanager.AccountPackage.User;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HelloController  {
    @FXML
    private Button login, newAccount;
    @FXML
    private TextField email;
    @FXML
    private PasswordField Password;
    @FXML
    private static Stage newaccount,loginstage;
    @FXML
   public void  login(ActionEvent event) throws IOException {
        if ( loginstage== null){
            String Email = email.getText();
            String password = Password.getText();
            Authentication.signIn(Email,password);

             //   User user = Authentication.signIn(Email, password);
             // User successfully signed in
             //   System.out.println("Welcome, " + user.getUserName() + "!");

                // Here you can open another scene or perform other actions
               // loginstage =(Stage) login.getScene().getWindow();
                loginstage=new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
                    loginstage.setScene(new Scene(root));
                    loginstage.setTitle("StockExchangeManager");
                    loginstage.show();
    }
        else{
            if (!loginstage.isShowing()) {
                loginstage.show();
            } else {
                loginstage.toFront();
            }
        }
    }

    @FXML
    public void  newAccount(ActionEvent event) throws IOException {
        if (newaccount == null) {
            //  Stage primarystage=(Stage) newAccount.getScene().getWindow();
            newaccount = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("newAccount.fxml"));
            newaccount.setScene(new Scene(root));
            newaccount.setTitle("StockExchangeManager");
            newaccount.show();
        } else {
            if (!newaccount.isShowing()) {
                newaccount.show();
            } else {
                newaccount.toFront();
            }
        }
    }








}
