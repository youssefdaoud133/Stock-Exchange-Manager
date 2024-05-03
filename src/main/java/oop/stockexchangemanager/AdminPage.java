package oop.stockexchangemanager;

import javafx.fxml.FXML;
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

public class AdminPage implements AccountPage {
    private static AdminPage instance;
    private Admin admin;
    private Stage userStage;

    private AdminPage(Admin admin) {
        this.admin = admin;
    }

    public static AdminPage getInstance(Admin admin) {
        if (instance == null) {
            instance = new AdminPage(admin);
        }
        return instance;
    }

    @Override
    public void showPage() {
        if (userStage != null && userStage.isShowing()) {
            userStage.toFront(); // If the stage is already showing, bring it to the front
        } else {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
                Parent root = loader.load(); // This line can throw an IOException

                // Access the controller of the loaded FXML
                AdminPageController controller = loader.getController();
                controller.setAdmin(admin); // Set the admin data in the controller

                userStage = new Stage();
                userStage.setScene(new Scene(root));
                userStage.setTitle("StockExchangeManager - " + admin.getUserName()); // Set title with admin's name
                userStage.show();

                userStage.setOnCloseRequest(event -> {
                    userStage = null; // Reset the stage when it's closed
                });

            } catch (IOException e) {
                e.printStackTrace(); // Print the stack trace to understand what caused the exception
                // Handle the exception, maybe show an error message to the user
            }
        }
    }
}
