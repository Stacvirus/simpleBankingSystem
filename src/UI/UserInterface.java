package UI;

import UI.domaine.Command;
import account.Account;
import account.domain.BusinessAccount;
import account.domain.SavingsAccount;
import bank.Bank;

import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Bank bank;
    private Account account;
    private Command cmd;

    public UserInterface(Scanner scanner) throws Exception{
        this.scanner = scanner;
        this.bank = new Bank("data.txt");
    }

    public void printFirstMenu(){
        this.print("Menu" +
                "\n-----------------" +
                "\n1. create account" +
                "\n2. Already have and account?" +
                "\nq. to quite");
    }

    public void start(){
        this.cmd = new Command(this, this.account, this.bank);

        this.print("Menu" +
                "\n------------------" +
                "\n1. create account" +
                "\n2. Already have and account?" +
                "\nq. to quite");

        while(true){
            String userInput = this.getUserInput("choose your option (1/2/q): ");

            if(userInput.equals("q")){
                break;
            }

            try {
                int input = Integer.parseInt(userInput);
                cmd.handleAccountChoice(input);
            }catch (Exception e){
                System.out.println("enter valid option!!");
            }
            printFirstMenu();
        }
        print("goodbye!!!");
    }

    public void print(String value){
        System.out.println(value);
    }

    public String getUserInput(String value){
        print(value);
        return scanner.nextLine();
    }

    public void secondMenu(){
        this.print("\n1. Deposit Money" +
                "\n2. Withdraw Money" +
                "\n3. Check your Balance" +
                "\n4. See History" +
                "\nq. go back" );

        while(true){
            String userInput = this.getUserInput("choose an option 1/2/3/4/q: ");
            if(userInput.equals("q")){
                break;
            }
            try{
                int input = Integer.parseInt(userInput);
                cmd.handleAccountServices(input);
            }catch (Exception e){
                System.out.println("Error: "+e.getMessage());
            }
        }
    }

    public void accountsMenu(){
        this.print("Type of Accounts" +
                "\n---------------" +
                "\n1. Savings account" +
                "\n2. Business account.Account");
    }
}
