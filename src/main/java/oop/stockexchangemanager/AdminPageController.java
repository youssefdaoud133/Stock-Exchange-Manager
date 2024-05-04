package oop.stockexchangemanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import oop.stockexchangemanager.AccountPackage.Account;
import oop.stockexchangemanager.AccountPackage.Admin;
import oop.stockexchangemanager.AccountPackage.User;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {

    @FXML
    private Button addStock;
    @FXML
    private Button showStock;
    @FXML
    private Button logoutButton;
    @FXML
    Button addButton;
    @FXML
    private Label adminName;

    @FXML
    private TextField companyName;
    @FXML
    private TextField quantity;
    @FXML
    private TextField price;

    @FXML
    private TableColumn<?, ?> companyNameSection;

    @FXML
    private TableColumn<?, ?> priceSection;

    @FXML
    private TableColumn<?, ?> quantitySection;
    @FXML
    private AnchorPane basePane;

    @FXML
    private AnchorPane addWindow;
    @FXML
    private AnchorPane stocksWindow;
    private double x =0;
    private double y =0;

    private Admin admin;
    public void switchWindows(ActionEvent event){
        if(event.getSource() == addStock){
            addWindow.setVisible(true);
            stocksWindow.setVisible(false);
        }
        else if(event.getSource() == showStock){
            addWindow.setVisible(false);
            stocksWindow.setVisible(true);
        }
    }
    public void logout(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if(option.get().equals(ButtonType.OK)){
                logoutButton.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void close(){
        System.exit(0);
    }
    public void minimize(){
        Stage stage = (Stage)basePane.getScene().getWindow();
        stage.setIconified(true);
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
        updateUI();
    }
    // Method to update UI with user data
    private void updateUI() {
        adminName.setText(admin.getUserName());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
