package oop.stockexchangemanager.Bank;

public class Deposit implements BankOperation {
    private static Deposit instance;

    // Private constructor to prevent instantiation outside this class
    private Deposit() {}

    // Method to get the instance of Deposit
    public static Deposit getInstance() {
        if (instance == null) {
            instance = new Deposit();
        }
        return instance;
    }
    @Override
    public void DoOperation(BankAccount account, float amount) {
        account.setBalance(account.getBalance() + amount);
    }
}

