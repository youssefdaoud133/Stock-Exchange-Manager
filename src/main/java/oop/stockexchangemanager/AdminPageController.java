package oop.stockexchangemanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import oop.stockexchangemanager.AccountPackage.Account;
import oop.stockexchangemanager.AccountPackage.Admin;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.Database.UserSubscriber;
import oop.stockexchangemanager.Database.Users;
import oop.stockexchangemanager.ExportOperation.CSVexporter;
import oop.stockexchangemanager.RTK.Rtk;
import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.StockPackage.StockOperation;
import oop.stockexchangemanager.Utils.AlterOperation;
import oop.stockexchangemanager.Utils.SendNotificitions;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AdminPageController  {
    public Label sessionState;
    private ObservableList<Stock> stocksList;
    private ObservableList<User> usersList;
    //
    @FXML
    private TableView<Stock> stocksTable;
    @FXML
    private TableColumn<Stock, String> companyNameSection;
    @FXML
    private TableColumn<Stock, Float> priceSection;
    @FXML
    private TableColumn<Stock,Integer> quantitySection;
    @FXML
    private TableColumn<Stock, String> adminNameSection;
    @FXML
    private TableColumn<Stock, Integer> stockIDSection;


    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, String> emailSection;

    @FXML
    private TableColumn<User, String> usernameSection;
    @FXML
    private TableColumn<User, String> balanceSection;



    public TextField companyNameField;
    public TextField quantityField;
    public TextField priceField;
    public TextField descroptionField;
    @FXML
    private Button logoutButton;
    @FXML
    private Label adminName;
    @FXML
    private AnchorPane addWindow;
    @FXML
    private AnchorPane stocksWindow;
    @FXML
    private AnchorPane usersWindow;
    @FXML
    private AnchorPane switchStateWindow;
    private double x =0;
    private double y =0;

    private Admin admin;
    public void switchState(ActionEvent event){
        Rtk.state=!Rtk.state;
        updateStateLabel();
        SendNotificitions.sendToSubscribesUsers("session opened");

        AlterOperation.showSuccessAlert("Stock added successfully.");
    }
    private void updateStateLabel() {
        if (Rtk.state) {
            sessionState.setText("Opened");
        } else {
            sessionState.setText("Closed");
        }
    }
    @FXML
    public void removeUser(ActionEvent event) {
        try {
            int selectedID=usersTable.getSelectionModel().getSelectedIndex();
            Users.getInstance().delete(usersTable.getItems().get(selectedID).getId());
            usersTable.getItems().remove(selectedID);
        } catch (Exception e) {

            AlterOperation.showErrorAlert("Failed to delete stock");
        }


    }
    @FXML
    public void view(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewGraph.fxml"));
            Parent root = loader.load(); // This line can throw an IOException

            // Access the controller of the loaded FXML
            ViewGraphController controller = loader.getController();

            // Get the selected stock and its history prices
            int selectedID = stocksTable.getSelectionModel().getSelectedIndex();
            Stock selectedStock = Stocks.getInstance().read(stocksTable.getItems().get(selectedID).getId());
            String companyName = selectedStock.getCompanyName();
            Stack<Float> historyPrices = selectedStock.getPriceHistory(); // Assuming this method exists

            // Pass the history prices to the controller
            controller.setHistoryPrices(historyPrices);

            Stage graphStage = new Stage();
            graphStage.setScene(new Scene(root));
            graphStage.setTitle("StockExchangeManager - " + admin.getUserName() + " - " + companyName);
            graphStage.show();

        } catch (Exception e) {

            AlterOperation.showErrorAlert("Failed to view stock");
        }
    }
    @FXML
    public void remove(ActionEvent event) {
        try {
            int selectedID=stocksTable.getSelectionModel().getSelectedIndex();
            Stocks.getInstance().delete(stocksTable.getItems().get(selectedID).getId());
            stocksTable.getItems().remove(selectedID);
        } catch (Exception e) {

            AlterOperation.showErrorAlert("Failed to delete stock");
        }
    }
    public void switchToStatesWindow(){
        addWindow.setVisible(false);
        stocksWindow.setVisible(false);
        usersWindow.setVisible(false);
        switchStateWindow.setVisible(true);
    }
public void switchToAddStock(){
    addWindow.setVisible(true);
    stocksWindow.setVisible(false);
    usersWindow.setVisible(false);
    switchStateWindow.setVisible(false);
}
public void switchToShowUsers(){
    addWindow.setVisible(false);
    stocksWindow.setVisible(false);
    usersWindow.setVisible(true);
    switchStateWindow.setVisible(false);
    usersList.clear();
    usersList.addAll(Users.getInstance().readAll());

    // Set the items of the TableView to the ObservableList
    usersTable.setItems(usersList);
}
public void switchToShowStock(){

    addWindow.setVisible(false);
    stocksWindow.setVisible(true);
    usersWindow.setVisible(false);
    switchStateWindow.setVisible(false);
    stocksList.clear();
    stocksList.addAll(Stocks.getInstance().readAll());

    // Set the items of the TableView to the ObservableList
    stocksTable.setItems(stocksList);

}
    public void addStock() {
        try {
            String companyName = companyNameField.getText();
            float price = Float.parseFloat(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String adminName = admin.getUserName();
            int adminId = admin.getId();

            Stock AddedStock = StockOperation.AddStock(companyName, price, quantity, adminName, adminId);
            stocksList.add(AddedStock);

            stocksTable.refresh();
            SendNotificitions.sendToSubscribesUsers(AddedStock.getCompanyName()+" added from admin");

            AlterOperation.showSuccessAlert("Stock added successfully.");
        } catch (Exception e) {
            AlterOperation.showErrorAlert(e.getMessage());
    }}

    public void logout(){

        try {
            boolean confirmed = AlterOperation.showConfirmationDialog("Are you sure you want to logout?");
            if(confirmed){
                logoutButton.getScene().getWindow().hide();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setAdmin(Admin admin) {
        this.admin = admin;
        updateUI();
    }
    // Method to update UI with user data
    private void updateUI() {
        adminName.setText(admin.getUserName());
    }

    @FXML
    public void initialize() {
        // Initialize ObservableList for customers
        stocksList = FXCollections.observableArrayList();
        usersList = FXCollections.observableArrayList();

        // Bind TableColumn to Customer properties
        companyNameSection.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        priceSection.setCellValueFactory(new PropertyValueFactory<>("priceHistory"));
        quantitySection.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        adminNameSection.setCellValueFactory(new PropertyValueFactory<>("AdminName"));
        stockIDSection.setCellValueFactory(new PropertyValueFactory<>("id"));
        stocksList.addAll(Stocks.getInstance().readAll());

        // Set the items of the TableView to the ObservableList
        stocksTable.setItems(stocksList);

        ///////////////////////////////////////////////////////
        // Bind TableColumn to Customer properties
        emailSection.setCellValueFactory(new PropertyValueFactory<>("Email"));
        usernameSection.setCellValueFactory(new PropertyValueFactory<>("UserName"));
        balanceSection.setCellValueFactory(new PropertyValueFactory<>("balance"));


        usersList.addAll(Users.getInstance().readAll());

        // Set the items of the TableView to the ObservableList
        usersTable.setItems(usersList);
    }


    public void exportData(ActionEvent event) {
        CSVexporter.createBlankCSV("src/main/java/oop/stockexchangemanager/historyCSV/StocksHistory.csv");
        List<String[]> dataToAdd = new ArrayList<>();
        String[] row0 = {"Company Name","Open Price" ,"Maximum price","Minimum Price","closed price"};
        dataToAdd.add(row0);
        List<Stock> ourStocks = new ArrayList<>();
        ourStocks.addAll(Stocks.getInstance().readAll());
        for(Stock stock: ourStocks){
            String[] row = new String[]{stock.getCompanyName(),String.valueOf(stock.getPriceHistory().getFirst()),String.valueOf(findMaxPrice(stock.getPriceHistory())),String.valueOf(findMinPrice(stock.getPriceHistory())),String.valueOf(stock.getPriceHistory().peek())};
            dataToAdd.add(row);
        }
        CSVexporter.writeDataToCSV("src/main/java/oop/stockexchangemanager/historyCSV/StocksHistory.csv" , dataToAdd);
        System.out.println("done");
    }

    private float findMinPrice(Stack<Float> historyPrices) {
        // Find the minimum price
        float minPrice = Float.MAX_VALUE;
        for (Float price : historyPrices) {
            if (price < minPrice) {
                minPrice = price;
            }
        }
        return minPrice; // Minimum price
    }

    private float findMaxPrice(Stack<Float> historyPrices) {
        // Find the maximum price
        float maxPrice = Float.MIN_VALUE;
        for (Float price : historyPrices) {
            if (price > maxPrice) {
                maxPrice = price;
            }
        }
        return maxPrice; // Maximum price
    }
}
