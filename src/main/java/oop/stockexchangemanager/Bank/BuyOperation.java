package oop.stockexchangemanager.Bank;

import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Database.Stocks;
import oop.stockexchangemanager.Database.UserStocks;
import oop.stockexchangemanager.Database.UserSubscriber;
import oop.stockexchangemanager.StockPackage.Stock;
import oop.stockexchangemanager.StockPackage.UserStock;
import oop.stockexchangemanager.UserPage;
import oop.stockexchangemanager.Utils.SendNotificitions;

import java.util.ArrayList;
import java.util.List;
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

     if(quantity<=  sellerUser.getOwnedStocks().read(userStock.getStockId()) ) {

         buyerUser.getOwnedStocks().update(userStock.getStockId(),quantity);
         sellerUser.getOwnedStocks().update(userStock.getStockId(),-quantity);
         WithDraw.getInstance().DoOperation(buyerUser.getBankAccount(),userStock.getUserPrice()* quantity);
         Deposit.getInstance().DoOperation(sellerUser.getBankAccount(),userStock.getUserPrice()* quantity);
         TransactionOpearation.buyFromUser(sellerUser,buyerUser,quantity,Stocks.getInstance().read(userStock.getStockId()),userStock.getUserPrice());
         Stocks.getInstance().read(userStock.getStockId()).setPrice(userStock.getUserPrice());
         userStock.setUserQuantity( userStock.getUserQuantity() - quantity );
         SendNotificitions.sendToSubscribesUsers(Stocks.getInstance().read(userStock.getStockId()).getCompanyName()+" have changed");




      }
      else {
          throw new IllegalArgumentException("Your quantity isn't enough");
      }
    }

}

