package oop.stockexchangemanager.AccountPackage;

import javafx.scene.control.DatePicker;
import oop.stockexchangemanager.Bank.BankAccount;
import oop.stockexchangemanager.Database.OwnedStocks;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class User extends Account{
    BankAccount bankAccount;
   OwnedStocks ownedStocks=new OwnedStocks();
   TransactionHistory transactionHistory=new TransactionHistory();

   Stack<String> notfications=new Stack<>();

  private boolean isSubscribed=false;

    public Stack<String> getNotfications() {
        return notfications;
    }

    public void setNotfications(String string) {
        this.notfications.push(string);
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }

    public void setOwnedStocks(OwnedStocks ownedStocks) {
        this.ownedStocks = ownedStocks;
    }

    public TransactionHistory getTransactionHistory() {
        return transactionHistory;
    }



    public OwnedStocks getOwnedStocks() {
        return ownedStocks;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public  String getBalance(){
        return (bankAccount.getBalance()+"");
    };
    public  Float getBalanceinFloat(){
        return (bankAccount.getBalance());
    };
    public Stack<Transaction> getTransactions() {
        return transactionHistory.getTransactions();
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

