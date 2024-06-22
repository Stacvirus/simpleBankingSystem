package UI.domaine;

import UI.UserInterface;
import account.Account;
import account.domain.BusinessAccount;
import account.domain.SavingsAccount;
import bank.Bank;

public class Command {
    private UserInterface userInterface;
    private Account account;
    private Bank bank;

    public Command(UserInterface ui, Account account, Bank bank){
        this.userInterface = ui;
        this.account = account;
        this.bank = bank;
    }

//    public void handleAccountChoice(int input) throws Exception{
//        switch (input){
//            case 1:
//                userInterface.accountsMenu();
//
//                int accountChoice = Integer.parseInt(userInterface.getUserInput("make your choice: "));
//                if(accountChoice == 1){
//                    this.account = new SavingsAccount(userInterface.getUserInput("Enter your the account's holder name: "));
//                } else if (accountChoice == 2) {
//                    this.account = new BusinessAccount(userInterface.getUserInput("Enter your the account's holder name: "));
//                } else {
//                    userInterface.print("wrong choice");
//                }
//
//                boolean ans = this.account.addBank(this.bank);
//                if(!ans){
//                    System.out.println("account already exist!");
//                }else {
//                    userInterface.secondMenu();
//                }
//                break;
//            case 2:
//                this.account = bank.getAccountByName(userInterface.getUserInput("Enter your account holder name: "));
//
//                if(this.account == null){
//                    userInterface.print("account not found!");
//                }else{
//                    userInterface.secondMenu();
//                }
//                break;
//            default:
//                userInterface.print("option not found!!!");
//        }
//    }
//
//    public void handleAccountServices(int input)throws Exception{
//        switch (input){
//            case 1:
//                this.account.deposit(Double.parseDouble(userInterface.getUserInput("Enter the amount of money: ")));
//                break;
//            case 2:
//                boolean ans = this.account.withdraw(Double.parseDouble(userInterface.getUserInput("Enter the amount of money: ")));
//                if(!ans){
//                    userInterface.print("insufficient amount for the operation: "+account.getBalance());
//                }
//                break;
//            case 3:
//                System.out.println(this.account);
//                break;
//            case 4:
//                account.printHistory();
//                break;
//            default:
//                userInterface.print("option not found!!!");
//        }
//    }
}
