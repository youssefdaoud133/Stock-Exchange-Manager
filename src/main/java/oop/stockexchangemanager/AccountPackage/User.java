package oop.stockexchangemanager.AccountPackage;

import javafx.scene.control.DatePicker;

public class User extends Account{

    public    static User  Geneate(String UserName, String Password, String Email, DatePicker birthdate) {

        User user = new User();
        user.id=Account.idGenerator;
        Account.idGenerator++;
        user.setUserName(UserName);
        user.setPassword(Password);
        user.setEmail(Email);
        user.setBirthdate(birthdate);
        return user;

    }
}

