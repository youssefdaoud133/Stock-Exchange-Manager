package oop.stockexchangemanager.AccountPackage;
import oop.stockexchangemanager.Database.stocks;

import java.util.Map;

public class stock  {
    String companyName;
    String AdminName;
    int AdminId;
    float price;
    int id;
    int Quantity;
    public static stock Generate(String companyName,float price,int Quantity,String AdminName)
    {
        stock Stock=new stock();
       Stock.id=Account.getIdGenerator();
       Account.setIdGenerator(Stock.id+1);
        Stock.setCompanyName(companyName);
        Stock.setAdminName(AdminName);
        Stock.setPrice(price);
        Stock.setQuantity(Quantity);
        return Stock;

    }
    public String getAdminName()
    {
        return AdminName;
    }
    private void setAdminName(String AdminName)
    {
        this.AdminName=AdminName;
    }
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        if (companyName != null && !companyName.isEmpty()) {
            this.companyName = companyName;
        } else {
            throw new IllegalArgumentException("Company name cannot be empty");
        }
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        if (price > 0) {
            this.price = price;
        } else {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
    }

    public int getId() {
        return id;
    }



    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.Quantity = quantity;
        } else {
            throw new IllegalArgumentException("Quantity must be non-negative");
        }
    }
    public Map<Integer, stocks> fetchStocks()
    {
       return stocks.getInstance().getData();
    }


}
