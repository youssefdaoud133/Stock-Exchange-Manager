package oop.stockexchangemanager.Utils;

import oop.stockexchangemanager.AccountPackage.User;
import oop.stockexchangemanager.Database.UserSubscriber;

import java.util.ArrayList;
import java.util.List;

public class SendNotificitions {
    public static void sendToSubscribesUsers(String msg){
        List<User> supscribesUsers = new ArrayList<>();
        supscribesUsers.addAll(UserSubscriber.getInstance().readAll());
        for(User user: supscribesUsers){
            user.setNotfications(msg);
        }
    }
}
