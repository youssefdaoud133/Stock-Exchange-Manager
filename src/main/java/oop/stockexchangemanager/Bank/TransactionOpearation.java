package oop.stockexchangemanager.Bank;

import oop.stockexchangemanager.AccountPackage.Transaction;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.StockPackage.Stock;

public class TransactionOpearation {
    // buy from admin or buy from user
    // method to add in the transaction history of the user a new transaction
    public static void buyFromAdmin(User user, int Quantity, Stock stock)
    {
       user.getTransactionHistory().addTransaction(new Transaction(user.getId(), stock.getAdminId(),"buy",Quantity, stock.getPrice(),Quantity*stock.getPrice(), stock.getId(),"admin" ));
    }
    public static void buyFromUser(User source, User destination,int Quantity,Stock stock)
    {
        destination.getTransactionHistory().addTransaction(new Transaction(destination.getId(), source.getId(),"buy",Quantity, stock.getPrice(),Quantity*stock.getPrice(), stock.getId(),"user" ));
    }
}
