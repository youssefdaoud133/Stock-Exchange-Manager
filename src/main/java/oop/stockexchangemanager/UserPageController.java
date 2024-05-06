package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import oop.stockexchangemanager.AccountPackage.User;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import oop.stockexchangemanager.RTK.Rtk;

public class UserPageController {
    @FXML
    private Label Username;
    @FXML
    private Label name;
    public Label Email;
    public Label password;
    public Label birthday;
    private User user;

    @FXML
    private Button logoutButton;

    @FXML
    private Button marketButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button shopButton;
    @FXML
    private AnchorPane marketWindow;
    @FXML
    private AnchorPane profileWindow;
    @FXML
    private AnchorPane shopWindow;

    public void setUser(User user) {
        this.user = user;
        updateUI();
    }
    @FXML
    public void initialize(){
         // Update UI with user data
    };

    // Method to update UI with user data
    private void updateUI() {
        Username.setText(user.getUserName());
        name.setText(user.getUserName());
        Email.setText(user.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedBirthday = user.getBirthdate().getValue().format(formatter);
        birthday.setText(formattedBirthday);
    }
    public void switchToProfile(){
        profileWindow.setVisible(true);
        marketWindow.setVisible(false);
        shopWindow.setVisible(false);
    }
    public void switchToMarket(){
        profileWindow.setVisible(false);
        marketWindow.setVisible(true);
        shopWindow.setVisible(false);
    }
    public void switchToShop(){
        profileWindow.setVisible(false);
        marketWindow.setVisible(false);
        shopWindow.setVisible(true);
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

}
