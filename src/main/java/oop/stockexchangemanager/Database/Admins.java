package oop.stockexchangemanager.Database;
import javafx.scene.control.DatePicker;
import oop.stockexchangemanager.AccountPackage.Admin;

public class Admins extends Collections<Admin> {

    // Private static variable to hold the instance of the class
    private static Admins instance;



    // Private constructor to prevent instantiation from outside
    private Admins() {
        // Initialize the data map or perform any other initialization
        super(); // Invoke the constructor of the parent class
        DatePicker datePicker = new DatePicker();
        Admin admin = Admin.Generate("youssefdaoud","12345678","youssef@gmail.com", datePicker );
        this.create(1 , admin);
         admin = Admin.Generate("salma ali","12345678","salma@gmail.com", datePicker );
        this.create(2 , admin);

    }

    // Static method to obtain the instance of the class
    public static synchronized Admins getInstance() {
        if (instance == null) {
            instance = new Admins();



        }

        return instance;
    }



}
