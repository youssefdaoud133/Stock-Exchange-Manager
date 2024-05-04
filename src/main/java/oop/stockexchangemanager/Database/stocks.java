package oop.stockexchangemanager.Database;

import oop.stockexchangemanager.AccountPackage.User;

public class stocks extends Collections<stocks>{
    private static stocks instance;
    private static int stockId=0;
    private int id=0;
// USER 1 =>1 USER2=>2  USER3=>3

    // Private constructor to prevent instantiation from outside
    private stocks() {
        // Initialize the data map or perform any other initialization
        super(); // Invoke the constructor of the parent class

    }
    // f
    //

    // Static method to obtain the instance of the class
    public static synchronized stocks getInstance() {
        if (instance == null) {
            instance = new stocks();
            stockId++;


        }

        return instance;
    }



}


