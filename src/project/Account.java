package project;

import java.util.Scanner;
import java.util.ArrayList;

public class Account implements Options {

    private String username;
    private String password;
    private AccountData a;

    public Account(String name, String pass) {
        this.username = name;
        this.password = pass;
        a = new AccountData();
    }

    /**
     * This method takes the user input for their new username and uses the changeUserName()
     * method in the class AccountData to change the username of the user in the files.
     */
    public void changeUsername(String newUsername) {
        a.changeUsername(username, newUsername);
        username = newUsername;
    }

    /**
     * This method takes the user input for their old password and checks if it is correct, so that their
     * account is not stolen. If it is correct, then the method will take the user input for the new
     * password and will use the changePassword() method in the AccountData class to change the user's
     * password accordingly. If not, then the user is taken back to the menu.
     */
    public void changePassword(String newPassword) {
        a.changePassword(username, password, newPassword);
        password = newPassword;
    }

    public static boolean accountIsMade(String username) {
        return MainFile.isMade(username + ".Cho");
    }
}
