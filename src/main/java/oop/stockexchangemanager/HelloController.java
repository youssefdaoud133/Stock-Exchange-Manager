package oop.stockexchangemanager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class HelloController  {
    @FXML
    private Button login, newAccount;
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;



    public void  login() throws IOException {
        Username.getText();
        Password.getText();
        Stage primarystage =(Stage) login.getScene().getWindow();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("scene2.fxml"));
        primaryStage.setScene(new Scene(root,500,500));
        primaryStage.setTitle("StockExchangeManager");
        primaryStage.show();
    }
    @FXML
    public void  newAccount() throws IOException {
        Stage primarystage=(Stage) newAccount.getScene().getWindow();
        Stage primaryStage=new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("newAccount.fxml"));
        primaryStage.setScene(new Scene(root,500,500));
        primaryStage.setTitle("StockExchangeManager");
        primaryStage.show();
    }


}
