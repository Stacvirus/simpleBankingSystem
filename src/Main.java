import UI.UserInterface;
import account.Account;
import bank.Bank;

import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try{
            UserInterface ui = new UserInterface(new Scanner(System.in));
            ui.start();
        }catch(Exception e){
            System.out.println("data base file not found!!");
        }
    }
}