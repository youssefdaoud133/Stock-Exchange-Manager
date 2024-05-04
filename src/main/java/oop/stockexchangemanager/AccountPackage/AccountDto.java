package oop.stockexchangemanager.AccountPackage;

public class AccountDto {
    public static boolean validateUserName(String userName) {

        return userName != null && userName.length() >= 3 && userName.length() <= 20;
    } // Placeholder logic, replace with your validation


    public static boolean validatePassword(String password) {
        if (password.length() >= 8){
            return true;
        }else return false;
    }

    public static  boolean validateEmail(String email) {
        // Regular expression for basic email format validation
        String regex = "^(.+)@(.+)$";

        // Check if the email is not null and matches the regex pattern
        return email != null && email.matches(regex);
    }
}
