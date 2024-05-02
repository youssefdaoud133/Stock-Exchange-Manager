package oop.stockexchangemanager.AccountPackage;

import javafx.scene.control.DatePicker;
import oop.stockexchangemanager.Database.Users;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Map;

//public class Authentication {
//   public static User SignUp(String UserName, String Password, String Email, DatePicker birthdate){
//       User user = User.Geneate(UserName,Password,Email,birthdate);
//       return  Users.getInstance().create(user.getId(),user);
//   }
//    public static User signIn(String email, String password) {
//        // Iterate through the HashMap containing user data
//        for (Map.Entry<Integer, User> entry : Users.getInstance().getData().entrySet()) {
//            User user = entry.getValue();
//            // Check if the email and password match
//            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
//                return user; // Return the user object if sign-in is successful
//            }
//        }
//        throw new RuntimeException("Invalid email or password"); // Throw exception if sign-in fails
//    }
//
//
//}
import java.security.*;
import java.util.Base64;

public class Authentication {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static User signUp(String userName, String password, String email, DatePicker birthdate) {
        // Hash the password before saving it
        String hashedPassword = hashPassword(password);
        User user = User.Geneate(userName, hashedPassword, email, birthdate);
        // Save the user to the database
        return Users.getInstance().create(user.getId(), user);
    }

    public static User signIn(String email, String password) {
        // Hash the password provided by the user
        String hashedPassword = hashPassword(password);
        // Iterate through the database to find a matching user
        for (Map.Entry<Integer, User> entry : Users.getInstance().getData().entrySet()) {
            User user = entry.getValue();
            // Check if the email matches
            if (user.getEmail().equals(email)) {
                // Check if the hashed passwords match
                if (user.getPassword().equals(hashedPassword)) {
                    return user; // Return the user object if sign-in is successful
                } else {
                    throw new RuntimeException("Incorrect password");
                }
            }
        }
        throw new RuntimeException("User not found"); // Throw exception if user not found
    }

    private static String hashPassword(String password) {
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error hashing password");
        }
    }
}

