package oop.stockexchangemanager.Database;

import oop.stockexchangemanager.AccountPackage.Account;
import oop.stockexchangemanager.AccountPackage.User;


import java.util.HashMap;
import java.util.Map;

public class Users extends Collections<User> {

    // Private static variable to hold the instance of the class
    private static Users instance;
    private static int UserId=0;
    private int id=0;


    // Private constructor to prevent instantiation from outside
    private Users() {
        // Initialize the data map or perform any other initialization
        super(); // Invoke the constructor of the parent class

    }

    // Static method to obtain the instance of the class
    public static synchronized Users getInstance() {
        if (instance == null) {
            instance = new Users();
            UserId++;


        }

        return instance;
    }



}
