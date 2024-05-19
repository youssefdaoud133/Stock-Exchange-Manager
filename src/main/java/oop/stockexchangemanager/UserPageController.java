package oop.stockexchangemanager;

import javafx.beans.property.SimpleStringProperty;
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
import oop.stockexchangemanager.Database.UserSubscriber;
import oop.stockexchangemanager.Database.Users;
import oop.stockexchangemanager.ExportOperation.CSVexporter;
import oop.stockexchangemanager.RTK.Rtk;
import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.StockPackage.UserStock;
import oop.stockexchangemanager.Utils.AlterOperation;
import oop.stockexchangemanager.Utils.PrintList;

public class UserPageController {
    private ObservableList<Stock> stocksList;
    @FXML
    private TableView<Stock> stocksTable;
    @FXML
    private TableColumn<Stock, String> companyNameSection;
    @FXML
    private TableColumn<Stock, Float> priceSection;
    @FXML
    private TableColumn<Stock,Integer> quantitySection1;
    @FXML
    private TableColumn<Stock, String> adminNameSection;
    @FXML
    private TableColumn<Stock, Integer> stockIDSection;
    public AnchorPane stocksWindow;

    private ObservableList<String> notificationsList;
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
    private TableView<String> notificationsTable;
    @FXML
    private TableColumn<String, String> notificationsColumn;
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

    @FXML
    public AnchorPane currentAnchorPane;

    public void setCurrentAnchorPane(AnchorPane anchorPane) {
        // Hide the currently visible pane
        if (currentAnchorPane != null) {

            currentAnchorPane.setVisible(false);
        }else {
            profileWindow.setVisible(false);
        }

        // Update the reference to the new pane and show it
        currentAnchorPane = anchorPane;
        currentAnchorPane.setVisible(true);
    }

    private void updateNotificationsList() {
        notificationsList.clear();
        notificationsList.addAll(user.getNotfications());
        notificationsTable.setItems(notificationsList);
    }


    public void setUser(User user) {
        this.user = user;
        updateUI();
        updateTransactionList();
    }

    public void updatePrice(){
        balance.setText(user.getBankAccount().getBalance()+"");
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
        if(Users.getInstance().read(user.getId()) !=null){
            setCurrentAnchorPane(notifications);

            updateNotificationsList();
            updateTransactionList();
            updatePrice();

        }else {
            AlterOperation.showErrorAlert("admin removed you");
        }

    }
        public void switchToTransections(){
            if(Users.getInstance().read(user.getId()) !=null){
                setCurrentAnchorPane(transectionsWindow);

                updateTransactionList();
                updatePrice();

            }else {
                AlterOperation.showErrorAlert("admin removed you");
            }

    }
    public void switchToProfile(){
        if(Users.getInstance().read(user.getId()) !=null){
            setCurrentAnchorPane(profileWindow);

            updatePrice();

        }else {
            AlterOperation.showErrorAlert("admin removed you");
        }

    }
    public void switchToOwnerShop(){
        if(Users.getInstance().read(user.getId()) !=null){
        if(Rtk.state) {
            setCurrentAnchorPane(OwnerShop);

            try {
                PrintList.populateOwnerStocksGrid(user, gridOwnerStock, scrollOwnerStock, "sellCard");
            } catch (IOException e) {
                e.printStackTrace();
            }
            updatePrice();
        }
        else
        {
            AlterOperation.showErrorAlert("the session is closed");
        }}else {
            AlterOperation.showErrorAlert("admin removed you");
        }
    }
    public void switchToMarket(){
        if(Users.getInstance().read(user.getId()) !=null){
        if(Rtk.state) {
            setCurrentAnchorPane(marketWindow);

            try {
                PrintList.populateStocksGrid(this,user, grid, scroll, "marketCard");
            } catch (IOException e) {
                e.printStackTrace();
            }
            updatePrice();
        }
        else
        {
            AlterOperation.showErrorAlert("the session is closed");
        }}else {
            AlterOperation.showErrorAlert("admin removed you");
        }
    }
    public void switchToShop(){
        if(Users.getInstance().read(user.getId()) !=null) {

            if (Rtk.state) {
                setCurrentAnchorPane(shopWindow);


                try {
                    PrintList.populateUserStocksGrid(this, user, gridUserStock, scrollUserStock, "userCard");
                    updatePrice();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                AlterOperation.showErrorAlert("the session is closed");
            }
            updatePrice();
        }else {
            AlterOperation.showErrorAlert("admin removed you");
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
        stocksList = FXCollections.observableArrayList();
        // Initialize ObservableList for customers
        transactionList = FXCollections.observableArrayList();
        notificationsList = FXCollections.observableArrayList();

        // Bind TableColumn to Customer properties
        companyNameSection.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        priceSection.setCellValueFactory(new PropertyValueFactory<>("priceHistory"));
        quantitySection1.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        adminNameSection.setCellValueFactory(new PropertyValueFactory<>("AdminName"));
        stockIDSection.setCellValueFactory(new PropertyValueFactory<>("id"));
        stocksList.addAll(Stocks.getInstance().readAll());

        // Set the items of the TableView to the ObservableList
        stocksTable.setItems(stocksList);

        notificationsColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));


        // Bind TableColumn to Customer properties
        amountSection.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
        quantitySection.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        sourceSection.setCellValueFactory(new PropertyValueFactory<>("sourceId"));
        sourceTypeSection.setCellValueFactory(new PropertyValueFactory<>("sourceType"));
        transectionTypeSection.setCellValueFactory(new PropertyValueFactory<>("transactionType"));



        notificationsTable.setItems(notificationsList);

        // Set the items of the TableView to the ObservableList
        transectionsTable.setItems(transactionList);


    }


    public void AddMoney(MouseEvent mouseEvent) {
        if(Users.getInstance().read(user.getId()) !=null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("deposit.fxml"));
                Parent root = loader.load(); // This line can throw an IOException

                // Access the controller of the loaded FXML
                DepositController controller = loader.getController();

                // Pass the history prices to the controller
                controller.setData(user);
                controller.setUserPageController(this);

                Stage graphStage = new Stage();
                graphStage.setScene(new Scene(root));
                graphStage.setTitle("StockExchangeManager - " + user.getUserName());
                graphStage.setOnHidden(event -> {
                    // Function to execute when the window is closed
                    updatePrice();
                });
                graphStage.show();

            } catch (Exception e) {
                System.out.println(e.getMessage());
                AlterOperation.showErrorAlert("Failed to view stock");
            }
        }else {
            AlterOperation.showErrorAlert("admin removed you");

        }
    }

    public void subscribe(ActionEvent event) {
        try {
            if(!user.isSubscribed()){
                UserSubscriber.getInstance().create(user.getId(), user);
                user.setSubscribed(true);
                AlterOperation.showSuccessAlert("congratulations you have subscribed");
            }else {
                AlterOperation.showSuccessAlert("already you subscribed");
            }

        }
        catch (Exception e)
        {
            AlterOperation.showErrorAlert(e.getMessage());
        }
    }
    public void export(ActionEvent event) {
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

    public void switchToStocksWindow(ActionEvent event) {
        if(Users.getInstance().read(user.getId()) !=null) {
        if(user.isSubscribed()){
            setCurrentAnchorPane(stocksWindow);


            stocksList.clear();
            stocksList.addAll(Stocks.getInstance().readAll());

            // Set the items of the TableView to the ObservableList
            stocksTable.setItems(stocksList);

        }else {
            AlterOperation.showErrorAlert("you are in community edition");
        }}else {
            AlterOperation.showErrorAlert("admin removed you");

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
            graphStage.setTitle("StockExchangeManager - " + user.getUserName() + " - " + companyName);
            graphStage.show();

        } catch (Exception e) {

            AlterOperation.showErrorAlert("Failed to view stock");
        }
    }
}
