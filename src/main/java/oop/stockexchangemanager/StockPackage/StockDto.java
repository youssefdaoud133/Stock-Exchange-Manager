package oop.stockexchangemanager.StockPackage;

public class StockDto {
    public static  boolean validateCompanyName(String companyName){
        return companyName != null && !companyName.isEmpty();
    }
    public static  boolean validatePrice(float price){
        return price > 0 ;
    }
    public static  boolean validateQuantity(int quantity){
        return quantity >= 0 ;
    }


}
