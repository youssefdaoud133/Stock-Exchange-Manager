package oop.stockexchangemanager.Database;

import oop.stockexchangemanager.AccountPackage.User;

public class UserSubscriber extends Collections<User> {
    private static UserSubscriber instance;


    // Private constructor to prevent instantiation from outside
    private UserSubscriber() {
        // Initialize the data map or perform any other initialization
        super(); // Invoke the constructor of the parent class

    }
    // f
    //

    // Static method to obtain the instance of the class
    public static synchronized UserSubscriber getInstance() {
        if (instance == null) {
            instance = new UserSubscriber();



        }

        return instance;
    }
}
