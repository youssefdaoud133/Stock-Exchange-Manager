package oop.stockexchangemanager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import oop.stockexchangemanager.AccountPackage.User;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.RTK.Rtk;
import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.Utils.AlterOperation;
import oop.stockexchangemanager.Utils.PrintList;

public class UserPageController {
    public Button Ownershop;
    public AnchorPane OwnerShop;
    public ScrollPane scrollOwnerStock;
    public GridPane gridOwnerStock;
    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;
    @FXML
    private Label Username;
    @FXML
    private Label balance;
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
        balance.setText(user.getBankAccount().getBalance()+"");
    }
    public void switchToProfile(){
        profileWindow.setVisible(true);
        marketWindow.setVisible(false);
        shopWindow.setVisible(false);
        OwnerShop.setVisible(false);
    }
    public void switchToOwnerShop(){
        profileWindow.setVisible(false);
        marketWindow.setVisible(false);
        shopWindow.setVisible(false);
        OwnerShop.setVisible(true);
        try {

            PrintList.populateOwnerStocksGrid(user,gridOwnerStock,scrollOwnerStock,"sellCard");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void switchToMarket(){
        profileWindow.setVisible(false);
        marketWindow.setVisible(true);
        shopWindow.setVisible(false);
        OwnerShop.setVisible(false);



        try {

            PrintList.populateStocksGrid(user,grid,scroll,"marketCard");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void switchToShop(){
        profileWindow.setVisible(false);
        marketWindow.setVisible(false);
        shopWindow.setVisible(true);
        OwnerShop.setVisible(false);

        //

    }
    public void logout(){

        try { boolean confirmed = AlterOperation.showConfirmationDialog("Are you sure you want to logout?");
            if(confirmed){
                logoutButton.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





}
