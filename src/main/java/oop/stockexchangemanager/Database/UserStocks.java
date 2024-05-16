package oop.stockexchangemanager.Database;

import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.StockPackage.UserStock;

public class UserStocks extends Collections<UserStock>{
    private static UserStocks instance;



    // Private constructor to prevent instantiation from outside
    private UserStocks() {
        // Initialize the data map or perform any other initialization
        super(); // Invoke the constructor of the parent class

    }

    // Static method to obtain the instance of the class
    public static synchronized UserStocks getInstance() {
        if (instance == null) {
            instance = new UserStocks();
        }

        return instance;
    }



}
