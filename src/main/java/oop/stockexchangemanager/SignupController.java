package oop.stockexchangemanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import oop.stockexchangemanager.AccountPackage.Authentication;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Database.Users;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class SignupController {
    @FXML
    private TextField Username;
    @FXML
    private PasswordField Password;
    @FXML
    private PasswordField confirmPassword;
    @FXML
    private TextField email;
    @FXML
    public DatePicker birthdate;
    @FXML
    private Button signup;
    public ChoiceBox choiceBox;
    public Label Test;

//    @FXML
//    public void initialize(){
//        choiceBox.getItems().addAll("Male", "Female","mohandes");
//
//
//    };

    public void getDate(ActionEvent actionEvent) {

    }


    public void getGender() {
        // Get the selected item from the choiceBox
        Object selectedItem = choiceBox.getValue();

        // Check if the selected item is not null and is an instance of String
        if (selectedItem != null && selectedItem instanceof String) {
            // Cast the selected item to String
            String selectedGender = (String) selectedItem;

            // Update your label with the selected gender
            Test.setText("Selected Gender: " + selectedGender);
        } else {
            // Handle the case where no item is selected or the selected item is not a String
            Test.setText("Please select a gender");
        }
    }
    public void  signup() throws IOException {
        Stage stage=(Stage) signup.getScene().getWindow();
        stage.close();
        String Email = email.getText();
        String password = Password.getText();
        String confirmPass = confirmPassword.getText();
    if(!Objects.equals(password, confirmPass))
        throw new IllegalArgumentException("Invalid confirm password");
        DatePicker selectedDate = birthdate;
        String UserName = Username.getText();



        Authentication.signUp(UserName,password,Email,selectedDate);
}





}
