package oop.stockexchangemanager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import oop.stockexchangemanager.AccountPackage.Admin;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.AccountPage.AccountPage;
import oop.stockexchangemanager.AdminPageController;
import oop.stockexchangemanager.Database.Admins;
import oop.stockexchangemanager.Database.Users;
import oop.stockexchangemanager.UserPageController;

import java.io.IOException;
import java.util.Map;

public class UserPage implements AccountPage {
    private User user;

    public UserPage(User user) {
        this.user = user;
    }

    @Override
    public void showPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserPage.fxml"));
            Parent root = loader.load(); // This line can throw an IOException

            // Access the controller of the loaded FXML
            UserPageController controller = loader.getController();
            controller.setUser(user);

            Stage userStage = new Stage();
            userStage.setScene(new Scene(root));
            userStage.setTitle("StockExchangeManager - " + user.getUserName());
            userStage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(); // Print the stack trace to understand what caused the exception
            // Handle the exception, maybe show an error message to the user
        }
    }


    }



