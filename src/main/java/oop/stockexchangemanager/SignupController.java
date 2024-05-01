package oop.stockexchangemanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import oop.stockexchangemanager.AccountPackage.Authentication;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Database.Users;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SignupController {

    public DatePicker birthdate;
    public ChoiceBox choiceBox;
    public Label Test;

    @FXML
    public void initialize(){
        choiceBox.getItems().addAll("Male", "Female","mohandes");
        

    };

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


}
