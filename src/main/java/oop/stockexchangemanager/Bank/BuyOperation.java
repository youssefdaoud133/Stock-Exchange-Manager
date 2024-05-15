package oop.stockexchangemanager.Bank;

import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.StockPackage.Stock;

import java.util.Map;

public class BuyOperation implements SingleAccountOperation {
    private static BuyOperation instance;

    // Private constructor to prevent instantiation outside this class
    private BuyOperation() {}

    // Method to get the instance of BuyOperation
    public static BuyOperation getInstance() {
        if (instance == null) {
            instance = new BuyOperation();
        }
        return instance;
    }

    @Override
    public void doOperation(BankAccount bankAccount, Integer quantity, Stock stock) {

      if(quantity <= stock.getQuantity()) {
          WithDraw.getInstance().DoOperation(bankAccount, quantity * stock.getPrice());
          stock.setQuantity(stock.getQuantity() - quantity);
           bankAccount.getUserAccount().getOwnedStocks().update(stock.getId(),quantity);
           TransactionOpearation.buyFromAdmin(bankAccount.getUserAccount(),quantity,stock);

      }
      else {
          throw new IllegalArgumentException("Your quantity isn't enough");
      }
    }
}

