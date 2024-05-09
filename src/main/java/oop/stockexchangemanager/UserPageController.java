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

public class UserPageController {
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
    }
    public void switchToMarket(){
        profileWindow.setVisible(false);
        marketWindow.setVisible(true);
        shopWindow.setVisible(false);
        List<Stock> stocks = new ArrayList<>();
        stocks.addAll(Stocks.getInstance().readAll());
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < stocks.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("marketCard.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

               MarketCardController itemController = fxmlLoader.getController();
                itemController.setData(stocks.get(i),user);
                System.out.println(stocks.get(i));

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);
                grid.setHgap(240); // Horizontal gap
                grid.setVgap(30); // Vertical gap
                scroll.setVvalue(1.0); // Scrolls to the bottom


                GridPane.setMargin(anchorPane, new Insets(10, 60, 10, 20));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
