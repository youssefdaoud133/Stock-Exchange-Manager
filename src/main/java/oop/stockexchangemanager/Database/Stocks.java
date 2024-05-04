package oop.stockexchangemanager.Database;

import oop.stockexchangemanager.StockPackage.Stock;

public class Stocks extends Collections<Stock>{
    private static Stocks instance;



    // Private constructor to prevent instantiation from outside
    private Stocks() {
        // Initialize the data map or perform any other initialization
        super(); // Invoke the constructor of the parent class

    }

    // Static method to obtain the instance of the class
    public static synchronized Stocks getInstance() {
        if (instance == null) {
            instance = new Stocks();
        }

        return instance;
    }



}


