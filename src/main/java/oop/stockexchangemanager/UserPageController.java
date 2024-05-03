package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import oop.stockexchangemanager.AccountPackage.User;
import java.time.format.DateTimeFormatter;

import oop.stockexchangemanager.RTK.Rtk;

public class UserPageController {
    public Label Username;
    public Label Email;
    public Label password;
    public Label birthday;
    private   User user;

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
        Email.setText(user.getEmail());
        password.setText(user.getPassword());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedBirthday = user.getBirthdate().getValue().format(formatter);
        birthday.setText(formattedBirthday);
    }

}
