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
        if (validateUserName(UserName))
            this.UserName = UserName;
         else
            throw new IllegalArgumentException("Invalid UserName.");

    }

    public void setPassword(String Password) {
        if (validatePassword(Password))
            this.Password = Password;
        else
            throw new IllegalArgumentException("Invalid Password.");

    }

    public void setEmail(String Email) {
        if (validateEmail(Email)) {
            this.Email = Email;
        } else {
            throw new IllegalArgumentException("Invalid Email.");
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
    private boolean validateUserName(String userName) {
        // Your validation logic for user name
        return userName != null && userName.length() >= 4 && userName.length() <= 20;
    } // Placeholder logic, replace with your validation


    private boolean validatePassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(regex);
    }

    private boolean validateEmail(String email) {
        // Regular expression for basic email format validation
        String regex = "^(.+)@(.+)$";

        // Check if the email is not null and matches the regex pattern
        return email != null && email.matches(regex);
    }

}


