package oop.stockexchangemanager.Bank;

public class WithDraw implements BankOperation {
    private static WithDraw instance;

    // Private constructor to prevent instantiation outside this class
    private WithDraw() {}

    // Method to get the instance of Withdraw
    public static WithDraw getInstance() {
        if (instance == null) {
            instance = new WithDraw();
        }
        return instance;
    }

    @Override
    public void DoOperation(BankAccount account, float amount) {
        if (amount <= account.getBalance()) {
            account.setBalance(account.getBalance() - amount);
        } else {
            throw new IllegalArgumentException("Your balance isn't enough");
        }
    }
}
