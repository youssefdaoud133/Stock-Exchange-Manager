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
import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.StockPackage.StockOperation;
import oop.stockexchangemanager.Utils.AlterOperation;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Stack;

public class AdminPageController  {
    private ObservableList<Stock> stocksList;
    @FXML
    private TableView<Stock> tableview;
    public TextField companyNameField;
    public TextField quantityField;
    public TextField priceField;


   public TextField descroptionField;
    @FXML
    private Button addStock;
    @FXML
    private Button showStock;
    @FXML
    private Button logoutButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addButton;

    @FXML
    private Label adminName;
    @FXML
    private ImageView logoutIcon;


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
    private AnchorPane basePane;

    @FXML
    private AnchorPane addWindow;
    @FXML
    private AnchorPane stocksWindow;
    @FXML
    private GridPane gridPane;
    private double x =0;
    private double y =0;

    private Admin admin;
    @FXML
    public void edit(ActionEvent event){

    }
    @FXML
    public void view(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewGraph.fxml"));
            Parent root = loader.load(); // This line can throw an IOException

            // Access the controller of the loaded FXML
            ViewGraphController controller = loader.getController();

            // Get the selected stock and its history prices
            int selectedID = tableview.getSelectionModel().getSelectedIndex();
            Stock selectedStock = Stocks.getInstance().read(tableview.getItems().get(selectedID).getId());

            // Retrieve the opening, closing, average, minimum, and maximum prices
            Float openingPrice = selectedStock.getOpeningPriceHistory();
            Float closingPrice = selectedStock.getClosingPriceHistory();
            Float averagePrice = selectedStock.getAveragePriceHistory();
            Float minimumPrice = selectedStock.getMinimumPriceHistory();
            Float maximumPrice = selectedStock.getMaximumPriceHistory();

            // Pass the history prices to the controller
            controller.setHistoryPrices(openingPrice, closingPrice, averagePrice, minimumPrice, maximumPrice);

            Stage graphStage = new Stage();
            graphStage.setScene(new Scene(root));
            graphStage.setTitle("StockExchangeManager - " + admin.getUserName() + " - " + selectedStock.getCompanyName());
            graphStage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace(); // Print the stack trace to understand what caused the exception
            // Handle the exception, maybe show an error message to the user
        }
    }






    @FXML
    public void remove(ActionEvent event) {
        try {
            int selectedID=tableview.getSelectionModel().getSelectedIndex();
            Stocks.getInstance().delete(tableview.getItems().get(selectedID).getId());
            tableview.getItems().remove(selectedID);
        } catch (Exception e) {

            AlterOperation.showErrorAlert("Failed to delete stock");
        }
    }
public void switchToAddStock(){
    addWindow.setVisible(true);
    stocksWindow.setVisible(false);
}
public void switchToShowStock(){
    addWindow.setVisible(false);
    stocksWindow.setVisible(true);
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

            tableview.refresh();
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

        // Bind TableColumn to Customer properties
        companyNameSection.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        priceSection.setCellValueFactory(new PropertyValueFactory<>("priceHistory"));
        quantitySection.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        adminNameSection.setCellValueFactory(new PropertyValueFactory<>("AdminName"));
        stockIDSection.setCellValueFactory(new PropertyValueFactory<>("id"));
        stocksList.addAll(Stocks.getInstance().readAll());

        // Set the items of the TableView to the ObservableList
        tableview.setItems(stocksList);
    }

}
