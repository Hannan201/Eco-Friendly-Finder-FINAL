package project;

import java.util.ArrayList;
import java.util.Scanner;

//name.clear() -> clear the arraylist
public class AccountData {
    public static void main(String[] args){
        AccountData test = new AccountData();
        GettingLinks search = new GettingLinks();
        Scanner in = new Scanner(System.in);
        ArrayList<String> testArray = new ArrayList<>();
        System.out.println("Username:");
        String n = in.nextLine();
        System.out.println("Password:");
        String p = in.nextLine();
        test.createAccount(n, p);
        System.out.println("Your username is " + test.getUserName(n));
        System.out.println("Your password is " + test.getPassword(n));

//        testArray = search.searchAmazon(3, testArray);
        System.out.println("Saving your links...");
        test.saveLinks(n, testArray);
        //System.out.println("Clearing search...");
        //testArray.clear();
        //System.out.println("Searching amazon again...");
        //search.searchAmazon(4, testArray);
//        System.out.println("Your saved links:");
//        test.viewSavedLinks(n);
//        System.out.println("Clearing file links...");
//        test.clearFileLinks(test.getUserName(n), test.getPassword(n));
//        System.out.println("Your empty list of saved links:");
//        test.viewSavedLinks(n);


//        System.out.println("Changing your username... What will be new username?");
//        String temp = in.nextLine();
//        test.changeUsername(n,temp);
//        n = temp;
//        System.out.println("Changing your password... What will be new password?");
//        test.changePassword(test.getUserName(n), test.getPassword(n), in.nextLine());
//        System.out.println("Your new username is " + test.getUserName(n));
//        System.out.println("Your new password is " + test.getPassword(n));
//        System.out.println("Your saved links:");
//        test.viewSavedLinks(test.getUserName(n));
//        System.out.println("Clearing file links...");
//        test.clearFileLinks(test.getUserName(n), test.getPassword(n));
//        System.out.println("Your empty list of saved links:");
//        test.viewSavedLinks(n);
    }

    /**
     * This method takes in a String, which specifies the name of the file to read from. It reads all
     * of the links stored inside that file and prints them all in a numbered list.
     *
     * @param name: A String storing the name of the file minus the '.Cho' suffix at the end.
     */
    public void viewSavedLinks(String name) {
        String line;
        MainFile mainFile = new MainFile(name + ".Cho");
        mainFile.openForReading();
        mainFile.getLine();
        mainFile.getLine();
        int i = 1;
        while((line = mainFile.getLine()) != null) {
                System.out.println(i + ": " + line);
                i += 1;
            }
       mainFile.closeForReading();
    }

    /**
     * This method takes in a String specifying the name of the file to write in and a String arrayList
     * containing all of the searched links that the method will store into the file. The method takes
     * every element in the arrayList and writes each of them on the consecutive line in the file
     * starting from the third line.
     *
     * @param name: A String storing the name of the file that the method will be writing to.
     * @param arrayList: A String arrayList containing the links retrieved from Amazon when the method
     * searchAmazon from the class GettingLinks is used.
     */
    public void saveLinks(String name, ArrayList<String> arrayList) {
        MainFile mainFile = new MainFile(name + ".Cho");
        mainFile.openForWriting(true);
        for (String s : arrayList) {
           mainFile.appendToFile(s);
        }
        mainFile.closeForWriting();
    }

    /**
     * This method takes in two Strings, which store the name of the file that the method will open
     * and the user's password. It clears the file and writes the user's username and password inside,
     * essentially clearing all of the saved links in that file.
     *
     * @param name: A String that contains the name of the file that will be opened by this method, which is
     * also the username of the user.
     * @param pass: A String that conatins the user's password.
     */
    public void clearFileLinks(String name, String pass){
        MainFile mainFile = new MainFile(name + ".Cho");
        mainFile.openForWriting(false);
        mainFile.writeToFile(name + "," + pass + "\n");
        mainFile.closeForWriting();
    }

    /**
     * This method takes two Strings: the user's username and password. The method stores the Strings on
     * the first line of a newly created file with the same name as the username with the second line being
     * empty space.
     *
     * @param name: A String containing the username of the user while they create their account.
     * @param pass: A String containing the password of the user's account
     */
    public boolean createAccount(String name, String pass) {
        MainFile mainFile = new MainFile(name + ".Cho");
        if(!mainFile.isMade()) {
            mainFile.openForWriting(false);
            mainFile.writeToFile(name + "," + pass + "\n");
            mainFile.closeForWriting();
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method takes in a String that specifies the name of the file where the method will retrieve
     * the user's username.
     * @param name: A String containing the name of the file that the method will open.
     * @return A String containing the user's username read from the file.
     */
    public String getUserName(String name) {
        String line;
        MainFile mainFile = new MainFile(name + ".Cho");
        mainFile.openForReading();
        while ((line = mainFile.getLine()) != null) {
            if (line.split(",")[0].equals(name)) {
                mainFile.closeForReading();
                return line.split(",")[0];
            }
        }
        mainFile.closeForReading();
        return null;
    }

    /**
     * This method takes in a String that specifies the name of the file where the method will retrieve
     * the user's password.
     * @param name: A String containing the name of the file that the method will open.
     * @return A String containing the user's password read from the file.
     */
    public String getPassword(String name) {
        String line;
        MainFile mainFile = new MainFile(name + ".Cho");
        mainFile.openForReading();
        while ((line = mainFile.getLine()) != null) {
            if (line.split(",")[0].equals(name)) {
                mainFile.closeForReading();
                return line.split(",")[1];
            }
        }
        mainFile.closeForReading();
        return null;
    }

    /**
     * This method takes in two Strings containing the user's old username and their new username, which
     * are used to specify the name of the file being opened and the new username that the method will use
     * to replace the old username in the file.
     *
     * @param oldName: A String containing the name of the file or the user's old username
     * @param newName: A String containing the new name of the file or the user's new username
     */
    public void changeUsername(String oldName, String newName) {
        MainFile mainFile = new MainFile(oldName + ".Cho");
        mainFile.replace(oldName, newName);
        mainFile.rename(newName + ".Cho");
    }

    /**
     * This method takes in three Strings which contain the user's username, their new password and their
     * old password. The Strings are used to specify the file that will be opened to change the password
     * and the old password that the method will replace with the new password.
     * @param name: A String containing the name of the file
     * @param newPass: A String containing the new password that the method replaces the old password with
     * @param oldPass: A String containing the old password that the method replaces
     */
    public void changePassword(String name, String oldPass, String newPass) {
        MainFile main = new MainFile(name + ".Cho");
        main.replace(oldPass, newPass);
    }
}