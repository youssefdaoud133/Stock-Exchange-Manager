package oop.stockexchangemanager.AccountPackage;
import java.util.ArrayList;
import java.util.List;
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

    // Method to read all transactions
    public List<Transaction> readAll() {
        // Create a new list to hold all transactions
        List<Transaction> allTransactions = new ArrayList<>(transactions);
        return allTransactions;
    }

    // Other methods as needed
}
