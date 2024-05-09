package oop.stockexchangemanager.Bank;

import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.StockPackage.Stock;

public interface SingleAccountOperation {
    void doOperation(BankAccount bankAccount, Integer quantity, Stock stock);

}
