package oop.stockexchangemanager.Bank;

import oop.stockexchangemanager.AccountPackage.User;

public class BankAccount {
    protected User userAccount;
    protected float balance;

    public User getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(User userAccount) {
        this.userAccount = userAccount;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public BankAccount(User userAccount) {
        this.userAccount = userAccount;
        this.setBalance(400);
    }

    public BankAccount(User userAccount, float balance) {
        this.userAccount = userAccount;
        this.balance = balance;
    }
}
