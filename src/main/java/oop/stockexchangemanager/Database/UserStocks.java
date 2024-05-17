package oop.stockexchangemanager.Database;

import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.StockPackage.UserStock;

import java.util.Iterator;
import java.util.Map;

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

    public void removeStocksByUserId(int userId) {
        Iterator<Map.Entry<Integer, UserStock>> iterator = data.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, UserStock> entry = iterator.next();
            if (entry.getValue().getUserId() == userId) {
                iterator.remove();
            }
        }
    }



}
