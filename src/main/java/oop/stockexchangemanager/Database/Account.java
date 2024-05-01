package oop.stockexchangemanager.Database;

import javafx.scene.control.DatePicker;

public abstract class Account {
      protected  static int  idGenerator =1;
      protected   int  id =0;
      protected String UserName;
      protected   String Password;
      protected  String Email;
    protected DatePicker birthdate;


    public DatePicker getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(DatePicker birthdate) {
        this.birthdate = birthdate;
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

    public void setEmail(String Email) {
       this.Email = Email;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getEmail() {
        return Email;
    }
}

