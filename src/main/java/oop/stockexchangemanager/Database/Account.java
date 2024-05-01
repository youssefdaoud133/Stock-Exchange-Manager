package oop.stockexchangemanager.Database;

public class Account {
      protected  static int  idGenerator =1;
      protected   int  id =0;
      protected String UserName;
      protected   String Password;
      protected  String UserMail;

   static Account Geneate(String UserName,String Password,String UserMail)
    {
        Account Acc = new Account();
        Acc.UserName=UserName;
        Acc.Password=Password;
        Acc.UserMail=UserMail;
        Acc.id=idGenerator;
        Account.idGenerator++;
        return  Acc;
    }

    public int getId() {
        return id;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
    // Accoun x;

    public void setPassword(String password) {
        Password = password;
    }

    public void setUserMail(String userMail) {
        UserMail = userMail;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getUserMail() {
        return UserMail;
    }
}

