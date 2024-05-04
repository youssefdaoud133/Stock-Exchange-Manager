package oop.stockexchangemanager.StockPackage;


import oop.stockexchangemanager.Database.Stocks;

public class StockOperation {
    public static Stock AddStock(String companyName, float price, int Quantity, String AdminName,int AdminId){
        Stock stock = Stock.Generate(companyName,price,Quantity,AdminName,AdminId);
        return  Stocks.getInstance().create(stock.getId(),stock);
    }

}
