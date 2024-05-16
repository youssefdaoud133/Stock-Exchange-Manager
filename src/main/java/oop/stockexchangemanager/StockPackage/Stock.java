package oop.stockexchangemanager.StockPackage;
import oop.stockexchangemanager.AccountPackage.Account;
import oop.stockexchangemanager.Database.Stocks;

import java.util.Map;
import java.util.Stack;

public class Stock {
    protected String companyName;
    protected String AdminName;
    protected int AdminId;
    protected Stack<Float> priceHistory = new Stack<>(); // Stack to hold the history of prices
    protected static int idGenerator = 1;
    protected int id;
    protected int Quantity;


    public static Stock Generate(String companyName, float price, int Quantity, String AdminName, int AdminId) {
        Stock stock = new Stock();
        stock.id = Stock.idGenerator;
        Stock.idGenerator++;
        stock.setCompanyName(companyName);
        stock.setAdminId(AdminId);
        stock.setAdminName(AdminName);
        stock.setPrice(price);
        stock.setQuantity(Quantity);
        return stock;

    }

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int adminId) {
        AdminId = adminId;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }

    public static void setIdGenerator(int idGenerator) {
        Stock.idGenerator = idGenerator;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdminName() {
        return AdminName;
    }

    private void setAdminName(String AdminName) {
        this.AdminName = AdminName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        if (StockDto.validateCompanyName(companyName)) {
            this.companyName = companyName;
        } else {
            throw new IllegalArgumentException("Company name cannot be empty");
        }
    }

    public float getPrice() {
        return priceHistory.peek(); // Returns the top element of the stack without removing it
    }


    public void setPrice(float price) {
        if (StockDto.validatePrice(price)) {
            priceHistory.push(price); // Pushes the price onto the stack
        } else {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
    }


    public Stack<Float> getPriceHistory() {
        return priceHistory;
    }

    public int getId() {
        return id;
    }


    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        if (StockDto.validateQuantity(quantity)) {
            this.Quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
    }

    public Float getOpeningPriceHistory() {
        if (!priceHistory.isEmpty()) {
            return priceHistory.firstElement(); // Returns the first input in the price history stack (opening price)
        } else {
            throw new IllegalStateException("Price history is empty");
        }
    }

    public Float getClosingPriceHistory() {
        if (!priceHistory.isEmpty()) {
            return priceHistory.peek(); // Returns the last input in the price history stack (closing price)
        } else {
            throw new IllegalStateException("Price history is empty");
        }
    }

    public Float getAveragePriceHistory() {
        if (priceHistory.isEmpty()) {
            throw new IllegalStateException("Price history is empty");
        }

        float sum = 0;
        for (Float price : priceHistory) {
            sum += price;
        }
        return sum / priceHistory.size(); // Calculate the average price

    }

    public Float getMinimumPriceHistory() {
        if (priceHistory.isEmpty()) {
            throw new IllegalStateException("Price history is empty");
        }

        float minPrice = priceHistory.get(0); // Initialize minPrice with the first price
        for (Float price : priceHistory) {
            if (price < minPrice) {
                minPrice = price; // Update minPrice if the current price is smaller
            }
        }
        return minPrice; // Return the minimum price

    }

    public Float getMaximumPriceHistory() {
        if (priceHistory.isEmpty()) {
            throw new IllegalStateException("Price history is empty");
        }

        float maxPrice = priceHistory.get(0); // Initialize maxPrice with the first price
        for (Float price : priceHistory) {
            if (price > maxPrice) {
                maxPrice = price; // Update maxPrice if the current price is larger
            }
        }
        return maxPrice; // Return the maximum price
    }
}



       