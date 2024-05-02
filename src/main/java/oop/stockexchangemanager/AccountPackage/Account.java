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

    public void setUserName(String userName) {
        try {
            if (AccountDto.validateUserName(userName)) {
                this.UserName = userName;
            } else {
                throw new IllegalArgumentException("Invalid Username");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error setting username: " + e.getMessage());
            // Optionally, you can rethrow the exception if needed
            // throw e;
        }
    }

    public void setPassword(String Password) {
        try {
            if (AccountDto.validatePassword(Password))
                this.Password = Password;
            else
                throw new IllegalArgumentException("Invalid Password");
        } catch (IllegalArgumentException e) {
            System.out.println("Error setting password: " + e.getMessage());
        }

    }

    public void setEmail(String Email) {
        try {
            if (AccountDto.validateEmail(Email)) {
                this.Email = Email;
            } else {
                throw new IllegalArgumentException("Invalid Email");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error setting email: " + e.getMessage());
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


