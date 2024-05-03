package oop.stockexchangemanager.AccountPage;

import oop.stockexchangemanager.AccountPackage.Account;
import oop.stockexchangemanager.AccountPackage.Admin;
import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.AdminPage;
import oop.stockexchangemanager.UserPage;

public class AccountPageFactory {
    public static AccountPage getPage(Account account) {
        if (account instanceof User) {
            return new UserPage((User) account);
        } else if (account instanceof Admin) {
            return  AdminPage.getInstance((Admin) account);
        }
        // Add more 'else if' statements for other account types
        throw new IllegalArgumentException("Unknown account type");
    }
}
