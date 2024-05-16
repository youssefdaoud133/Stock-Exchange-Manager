package oop.stockexchangemanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import oop.stockexchangemanager.AccountPackage.Transaction;
import oop.stockexchangemanager.AccountPackage.User;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;

import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.Database.Users;
import oop.stockexchangemanager.RTK.Rtk;
import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.StockPackage.UserStock;
import oop.stockexchangemanager.Utils.AlterOperation;
import oop.stockexchangemanager.Utils.PrintList;

public class UserPageController {
    public ScrollPane scrollUserStock;
    public GridPane gridUserStock;
    private ObservableList<Transaction> transactionList;
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
    private Button notificationsButton;
    @FXML
    private Button logoutButton;

    @FXML
    private Button marketButton;

    @FXML
    private Button profileButton;

    @FXML
    private Button shopButton;
    @FXML
    private Button transectionsButton;
    @FXML
    private AnchorPane marketWindow;
    @FXML
    private AnchorPane profileWindow;
    @FXML
    private AnchorPane shopWindow;
    @FXML
    private AnchorPane transectionsWindow;
    @FXML
    private AnchorPane notifications;

    @FXML
    private TableView<?> notificationsTable;
    @FXML
    private TableColumn<?, ?> notificationsColumn;
    @FXML
    private TableView<Transaction> transectionsTable;
    @FXML
    private TableColumn<UserStock, Double> amountSection;
    @FXML
    private TableColumn<Transaction, Integer> quantitySection;
    @FXML
    private TableColumn<Transaction,Integer> sourceSection;
    @FXML
    private TableColumn<Transaction, String> sourceTypeSection;
    @FXML
    private TableColumn<Transaction, String> transectionTypeSection;
 

    public void setUser(User user) {
        this.user = user;
        updateUI();
        updateTransactionList();
    }


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
    private void updateTransactionList() {
        transactionList.clear();
        transactionList.addAll(user.getTransactionHistory().readAll());
        transectionsTable.setItems(transactionList);
    }
    public void switchToNotifications(){
        profileWindow.setVisible(false);
        marketWindow.setVisible(false);
        shopWindow.setVisible(false);
        OwnerShop.setVisible(false);
        transectionsWindow.setVisible(false);
        notifications.setVisible(true);
        updateTransactionList();
    }
        public void switchToTransections(){
        profileWindow.setVisible(false);
        marketWindow.setVisible(false);
        shopWindow.setVisible(false);
        OwnerShop.setVisible(false);
        transectionsWindow.setVisible(true);
        notifications.setVisible(false);
            updateTransactionList();
    }
    public void switchToProfile(){
        profileWindow.setVisible(true);
        marketWindow.setVisible(false);
        shopWindow.setVisible(false);
        OwnerShop.setVisible(false);
        transectionsWindow.setVisible(false);
        notifications.setVisible(false);
    }
    public void switchToOwnerShop(){
        if(Rtk.state) {
            profileWindow.setVisible(false);
            marketWindow.setVisible(false);
            shopWindow.setVisible(false);
            transectionsWindow.setVisible(false);
            OwnerShop.setVisible(true);
            notifications.setVisible(false);
            try {
                PrintList.populateOwnerStocksGrid(user, gridOwnerStock, scrollOwnerStock, "sellCard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            AlterOperation.showErrorAlert("the session is closed");
        }
    }
    public void switchToMarket(){
        if(Rtk.state) {
            profileWindow.setVisible(false);
            marketWindow.setVisible(true);
            shopWindow.setVisible(false);
            OwnerShop.setVisible(false);
            transectionsWindow.setVisible(false);
            notifications.setVisible(false);
            try {
                PrintList.populateStocksGrid(user, grid, scroll, "marketCard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            AlterOperation.showErrorAlert("the session is closed");
        }
    }
    public void switchToShop(){
        if(Rtk.state) {
            profileWindow.setVisible(false);
            marketWindow.setVisible(false);
            shopWindow.setVisible(true);
            OwnerShop.setVisible(false);
            transectionsWindow.setVisible(false);
            notifications.setVisible(false);
            try {
                PrintList.populateUserStocksGrid(user, gridUserStock, scrollUserStock, "userCard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            AlterOperation.showErrorAlert("the session is closed");
        }
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

    @FXML
    public void initialize() {
        // Initialize ObservableList for customers
        transactionList = FXCollections.observableArrayList();

        // Bind TableColumn to Customer properties
        amountSection.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        quantitySection.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        sourceSection.setCellValueFactory(new PropertyValueFactory<>("sourceId"));
        sourceTypeSection.setCellValueFactory(new PropertyValueFactory<>("sourceType"));
        transectionTypeSection.setCellValueFactory(new PropertyValueFactory<>("transactionType"));


        // Set the items of the TableView to the ObservableList
        transectionsTable.setItems(transactionList);


    }


    public void AddMoney(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("deposit.fxml"));
            Parent root = loader.load(); // This line can throw an IOException

            // Access the controller of the loaded FXML
            DepositController controller = loader.getController();

            // Pass the history prices to the controller
            controller.setData(user);

            Stage graphStage = new Stage();
            graphStage.setScene(new Scene(root));
            graphStage.setTitle("StockExchangeManager - " + user.getUserName() );
            graphStage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            AlterOperation.showErrorAlert("Failed to view stock");
        }
        System.out.println("from add money");
    }

    public void subscribe(ActionEvent event) {
    }
    public void export(ActionEvent event) {
    }
}
