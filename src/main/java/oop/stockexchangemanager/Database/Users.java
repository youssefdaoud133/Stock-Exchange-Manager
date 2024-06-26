package oop.stockexchangemanager.Database;

import oop.stockexchangemanager.AccountPackage.Account;
import oop.stockexchangemanager.AccountPackage.Admin;
import oop.stockexchangemanager.AccountPackage.User;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
//
public class Users extends Collections<User> {

    // Private static variable to hold the instance of the class
    private static Users instance;


    // Private constructor to prevent instantiation from outside
    private Users() {
        // Initialize the data map or perform any other initialization
        super(); // Invoke the constructor of the parent class

    }
    // f
    //

    // Static method to obtain the instance of the class
    public static synchronized Users getInstance() {
        if (instance == null) {
            instance = new Users();



        }

        return instance;
    }
    // Method to check if email already exists
    public boolean emailExists(String email) {
        Collection<User> users = readAll();
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        Collection<Admin> admins = Admins.getInstance().readAll();
        for (Admin admin : admins) {
            if (admin.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }
    public boolean uaernameExists(String username) {
        Collection<User> users = readAll();
        for (User user : users) {
            if (user.getUserName().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }



}
