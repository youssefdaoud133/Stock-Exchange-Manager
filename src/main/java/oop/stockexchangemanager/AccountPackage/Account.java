package oop.stockexchangemanager.AccountPackage;

import javafx.scene.control.DatePicker;

public abstract class Account {
      protected  static int  idGenerator =1;
      protected   int  id ;
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

    public void setUserName(String UserName) {
        if (AccountDto.validateUserName(UserName))
            this.UserName = UserName;
         else
            throw new IllegalArgumentException("Invalid Username");

    }

    public void setPassword(String Password) {
        if (AccountDto.validatePassword(Password))
            this.Password = Password;
        else
            throw new IllegalArgumentException("Invalid Password");

    }

    public void setEmail(String Email) {
        if (AccountDto.validateEmail(Email)) {
            this.Email = Email;
        } else {
            throw new IllegalArgumentException("Invalid Email");
        }
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


