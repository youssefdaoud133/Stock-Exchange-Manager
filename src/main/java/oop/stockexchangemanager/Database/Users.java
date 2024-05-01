package oop.stockexchangemanager.Database;

import java.util.HashMap;
import java.util.Map;

public class Users extends Collections<Account>{

    private Map<Integer, Account> userMap = new HashMap<>();




    @Override
    Account create() {
        Account a = Account.Geneate("khalaf", "12345678","khalaf@gmail.com");
        userMap.put(a.getId(), a);
        return a;
    }

    @Override
    Account read() {
        return null;
    }

    @Override
    Account update() {
        return null;
    }

    @Override
    boolean delete() {
        return false;
    }
}
