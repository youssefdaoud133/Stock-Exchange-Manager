package oop.stockexchangemanager.AccountPackage;
import java.util.Stack;

public class TransactionHistory {
    private Stack<Transaction> transactions;

    public TransactionHistory() {
        transactions = new Stack<>();
    }

    public Stack<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.push(transaction);
    }

    // Other methods as needed
}
