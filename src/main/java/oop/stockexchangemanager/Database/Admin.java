package oop.stockexchangemanager.Database;

import javafx.scene.control.DatePicker;

public class Admin extends Account{

  public    static Admin  Geneate(String UserName, String Password, String Email, DatePicker birthdate) {
      Admin admin = new Admin();
      admin.setUserName(UserName);
      admin.setPassword(Password);
      admin.setEmail(Email);
      admin.setBirthdate(birthdate);
      return admin;

    }

}
