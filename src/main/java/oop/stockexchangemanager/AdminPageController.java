package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import oop.stockexchangemanager.AccountPackage.Account;
import oop.stockexchangemanager.AccountPackage.Admin;
import oop.stockexchangemanager.AccountPackage.User;

import java.time.format.DateTimeFormatter;

public class AdminPageController {
    public Label Username;
    public Label Email;
    public Label password;
    public Label birthday;
    private Admin admin;

    public void setAdmin(Admin admin) {
        this.admin = admin;
        updateUI();
    }

    @FXML
    public void initialize(){
         // Update UI with user data


    };

    // Method to update UI with user data
    private void updateUI() {
        Username.setText(admin.getUserName());
        Email.setText(admin.getEmail());
        password.setText(admin.getPassword());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
//        String formattedBirthday = admin.getBirthdate().getValue().format(formatter);
//        birthday.setText(formattedBirthday);
    }

}
