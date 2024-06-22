package account;

import bank.Bank;

import java.util.*;

public class Account {
    private String holderName;
    private int number;
    private double balance;
    private Bank bank;
    private String fileName;

    public Account(String name){
        this(name, 0);
    }

    public Account(String name, double balance){
        this.holderName = name;
        this.number = this.generateAccountNumber();
        this.balance = balance;
        this.bank = null;
    }
    public boolean addBank(Bank bank){
        this.bank = bank;
        return this.bank.addAccount(holderName, balance, number);
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
        return "account holder name: "+this.holderName
            +"\nBalance: "+this.balance+" FCFA"
            +"\naccount number: "+this.number
                + "\nbank info: "+this.bank;
    }

    public double getBalance(){
        System.out.println("in getting balance");
        return balance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setBalance(double balance) {
        this.balance = balance;
        //bank.updateAccountData(this.balance, number);
    }

    public boolean deposit(double amount){

        if(amount > 0){
            this.balance += amount;
            bank.updateAccountData(this.balance, this.number);
            bank.setTransactionData(this.holderName, amount);
            return true;
        }
        System.out.println("Error: invalid amount of money");
        return false;
    }

    public boolean withdraw(double amount){
        if(amount > 0 && amount < balance){
            this.balance -= amount;
            bank.updateAccountData(balance, number);
            bank.setTransactionData(holderName, amount * -1);
            return true;
        }
        System.out.println("Error: insufficient amount of money");
        return false;
    }

    public String toHistory(){
        return bank.getHistoryTransaction(holderName);
    }
}
