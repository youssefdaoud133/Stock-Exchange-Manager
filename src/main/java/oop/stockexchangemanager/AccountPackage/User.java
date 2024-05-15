package oop.stockexchangemanager.AccountPackage;

import javafx.scene.control.DatePicker;
import oop.stockexchangemanager.Bank.BankAccount;
import oop.stockexchangemanager.Database.OwnedStocks;

import java.util.HashMap;
import java.util.Map;

public class User extends Account{
    BankAccount bankAccount;
     OwnedStocks ownedStocks=new OwnedStocks();

    public OwnedStocks getOwnedStocks() {
        return ownedStocks;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }



    public    static User  Generate(String UserName, String Password, String Email, DatePicker birthdate) {

        User user = new User();

        user.id=Account.idGenerator;
        Account.idGenerator++;
        user.setUserName(UserName);
        user.setPassword(Password);
        user.setEmail(Email);
        user.setBirthdate(birthdate);
        user.bankAccount =new BankAccount(user);
        return user;

    }
}

