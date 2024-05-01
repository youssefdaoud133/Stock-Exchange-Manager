package oop.stockexchangemanager.Database;
import oop.stockexchangemanager.AccountPackage.Admin;

public class Admins extends Collections<Admin> {

    // Private static variable to hold the instance of the class
    private static Admins instance;

    // Private constructor to prevent instantiation from outside
    private Admins() {
        // Initialize the data map or perform any other initialization
        super(); // Invoke the constructor of the parent class
    }

    // Static method to obtain the instance of the class
    public static synchronized Admins getInstance() {
        if (instance == null) {
            instance = new Admins();
        }
        return instance;
    }



}
