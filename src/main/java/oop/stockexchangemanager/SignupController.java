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
    public Label ErrorMsg;
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

    private static Stage stage;
    // Setter method for stage
    public static void setStage(Stage stage) {
        SignupController.stage = stage;
    }

    public void  signup() throws IOException {
        try{
            String UserName = Username.getText();
            String Email = email.getText();
            String password = Password.getText();
            String confirmPass = confirmPassword.getText();
            DatePicker selectedDate = birthdate;
            if(!Objects.equals(password, confirmPass)) {
                throw new IllegalArgumentException("Invalid confirm password");
            }
            Authentication.SignUp(UserName,password,Email,selectedDate);
            SignupController.stage.close();


        }catch (Exception e){
            ErrorMsg.setText(e.getMessage());

        };

    }



}
