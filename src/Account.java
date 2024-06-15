import java.util.ArrayList;
import java.util.Random;

public class Account {
    private String holderName;
    private int number;
    private double balance;
    private ArrayList<String> history;
    public Bank bank;
    private double interestRate;

    public Account(String name){
        this(name, 0);
    }

    public Account(String name, double balance){
        this.holderName = name;
        this.number = this.generateAccountNumber();
        this.balance = balance;
        this.history = new ArrayList<>();
        this.bank = null;
    }
    public void addBank(Bank bank){
        this.bank = bank;
    }

    public int generateAccountNumber(){
        Random rand = new Random();
        return rand.nextInt(1000000000);
    }

    public String getHolderName(){
        return this.holderName;
    }

    @Override
    public String toString(){
        return "Account holder name: "+this.holderName
            +"\nBalance: "+this.balance+" FCFA"
            +"\nAccount number: "+this.number;
    }

    public double getBalance(){
        return this.balance;
    }

    public double deposite(double amount){
        this.history.add("deposit: "+amount);
        return this.balance += amount;
    }

    public boolean withdraw(double amount){
        if(this.balance > 0){
            this.balance -= amount;
            this.history.add("withdraw: "+amount);
            return true;
        }
        return false;
    }

    public int getNumber(){
        return this.number;
    }

    public void printHistory(){
        System.out.println(this.holderName+" account's transaction history: ");
        String ans = "";

        for(String transaction: history){
            ans += transaction+"\n";
        }

        if(this.history.isEmpty()){
            ans = "no transactions for the moment!";
        }
        System.out.println(ans+"\n-----------");
    }

    public boolean deductFees(double fees){
        if(this.balance > 0){
            this.balance -= fees;
            return true;
        }
        return false;
    }
}
