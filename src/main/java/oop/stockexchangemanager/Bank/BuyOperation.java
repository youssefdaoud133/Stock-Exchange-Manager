package oop.stockexchangemanager.Bank;

import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.StockPackage.UserStock;

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
    @Override
    public void buyFromUser(Integer quantity,User buyerUser, User sellerUser, UserStock userStock) {
        // change in the bankaccount in the buyer and seller
        // update in the owned stocks in the buyer and seller
        // update in the transaction history in the buyer and seller
;

     if(quantity<=userStock.getUserQuantity()) {
         buyerUser.getOwnedStocks().update(userStock.getStockId(),quantity);
         sellerUser.getOwnedStocks().update(userStock.getStockId(),-quantity);
         WithDraw.getInstance().DoOperation(buyerUser.getBankAccount(),userStock.getUserPrice()* quantity);
         Deposit.getInstance().DoOperation(sellerUser.getBankAccount(),userStock.getUserPrice()* quantity);
         TransactionOpearation.buyFromUser(sellerUser,buyerUser,quantity,Stocks.getInstance().read(userStock.getId()),userStock.getUserPrice());
         Stocks.getInstance().read(userStock.getStockId()).setPrice(userStock.getUserPrice());
      }
      else {
          throw new IllegalArgumentException("Your quantity isn't enough");
      }
    }

}

