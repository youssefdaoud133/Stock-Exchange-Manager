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
   public void  login(ActionEvent event) throws IOException {
        login.setOnAction(e -> {
            String Email = email.getText();
            String password = Password.getText();
            try {
             //   User user = Authentication.signIn(Email, password);
             // User successfully signed in
             //   System.out.println("Welcome, " + user.getUserName() + "!");
                System.out.println(Email);
                System.out.println(password);
                // Here you can open another scene or perform other actions
                Stage primarystage =(Stage) login.getScene().getWindow();
                Stage primaryStage=new Stage();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
                    primaryStage.setScene(new Scene(root));
                    primaryStage.setTitle("StockExchangeManager");
                    primaryStage.show();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (RuntimeException exception) {
                // Sign-in failed
                System.out.println(exception.getMessage());
            }
        });

    }
    @FXML
    public void  newAccount(ActionEvent event) throws IOException {

        Stage primarystage=(Stage) newAccount.getScene().getWindow();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("newAccount.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("StockExchangeManager");
        primaryStage.show();

    }
}
