package oop.stockexchangemanager.Bank;

import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.StockPackage.UserStock;

public interface SingleAccountOperation {
    void doOperation(BankAccount bankAccount, Integer quantity, Stock stock);
     void buyFromUser(Integer quantity,User buyerUser, User sellerUser, UserStock userStock);

}
