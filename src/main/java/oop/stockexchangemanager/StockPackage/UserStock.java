package oop.stockexchangemanager.StockPackage;

public class UserStock {
    private   static int  idGenerator =1;
    private    int  id ;
    private  int userId;// for the seller
    private  int stockId;
    private  float userPrice;
    private  int userQuantity;


    public static UserStock Generate(int userId, int stockId, float userPrice,int userQuantity)
    {
        UserStock userStock=new UserStock();
        userStock.id=Stock.idGenerator;
        UserStock.idGenerator++;
        userStock.setUserId(userId);
        userStock.setStockId(stockId);
        userStock.setUserPrice(userPrice);
        userStock.setUserQuantity(userQuantity);
        return userStock;
    }


    public int getUserQuantity() {
        return userQuantity;
    }

    public void setUserQuantity(int userQuantity) {
        this.userQuantity = userQuantity;
    }

    public static int getIdGenerator() {
        return idGenerator;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public float getUserPrice() {
        return userPrice;
    }

    public void setUserPrice(float userPrice) {
        if (StockDto.validatePrice(userPrice)) {
            this.userPrice = userPrice;
        } else {
            throw new IllegalArgumentException("Price must be greater than zero");
        }

    }
}
