import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private Bank bank;
    private Account account;

    public UserInterface(Scanner scanner){
        this.scanner = scanner;
        this.bank = new Bank();
    }

    public void printFirstMenu(){
        this.print("Menu\n------" +
                "\n1. create Account" +
                "\n2. Already have and account?" +
                "\nq. to quite");
    }

    public void start(){
        this.print("Menu\n------" +
                "\n1. create Account" +
                "\n2. Already have and account?" +
                "\nq. to quite");
        while(true){
            String userInput = this.getUserInput("choose your option (1/2/q): ");

            if(userInput.equals("q")){
                break;
            }

            int input = Integer.parseInt(userInput);
            switch (input){
                case 1:
                    this.accountsMenu();

                    int accountChoice = Integer.parseInt(this.getUserInput("make your choice: "));
                    if(accountChoice == 1){
                        this.account = new SavingsAccount(this.getUserInput("Enter your the account's holder name: "));
                    } else if (accountChoice == 2) {
                        this.account = new BusinessAccount(this.getUserInput("Enter your the account's holder name: "));
                    } else {
                        this.print("wrong choice");
                        continue;
                    }

                    this.bank.addAccount(account);
                    this.secondMenu(this.account);
                    break;
                case 2:
                    this.account = bank.getAccountByName(this.getUserInput("Enter your account holder name: "));

                    if(this.account == null){
                        print("Account not found!");
                    }else{
                        this.secondMenu(this.account);
                    }
                    break;
                default:
                    print("option not found!!!");
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

    public void secondMenu(Account account){
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
            int input = Integer.parseInt(userInput);
            switch (input){
                case 1:
                    this.account.deposite(Double.parseDouble(getUserInput("Enter the amount of money: ")));
                    break;
                case 2:
                    this.account.withdraw(Double.parseDouble(getUserInput("Enter the amount of money: ")));
                    break;
                case 3:
                    System.out.println(this.account);
                    break;
                case 4:
                    account.printHistory();
                    break;
                default:
                    this.print("option not found!!!");
            }
        }
    }

    public void accountsMenu(){
        this.print("Type of Accounts" +
                "\n---------------" +
                "\n1. Savings account" +
                "\n2. Business Account");
    }
}
