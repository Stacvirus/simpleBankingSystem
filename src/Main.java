import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface(new Scanner(System.in));
        try{
            ui.start();
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
//        Bank bank = new Bank();
//        Account user = new Account("stac virus");
//        System.out.println(user);
//        bank.addAccount(user);
//        System.out.println(user.bank.numberOfAccounts());
//        System.out.println(user.bank.getAccountByNumber(user));
    }
}