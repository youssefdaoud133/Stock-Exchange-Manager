package oop.stockexchangemanager.Bank;

public class WithDraw implements BankOperation{
    @Override
    public void DoOperation(BankAccount account,float amount) {
      if(amount<= account.balance)  {
          account.balance-=amount;
      }
      else {
          throw new IllegalArgumentException("Your balance isn't enough");
      }


    }
}
