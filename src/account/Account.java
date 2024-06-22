package account;

import bank.Bank;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public boolean addBank(Bank bank)throws Exception{
        this.bank = bank;
        return this.bank.addAccount(this);
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
        return "account.Account holder name: "+this.holderName
            +"\nBalance: "+this.balance+" FCFA"
            +"\naccount.Account number: "+this.number;
    }

    public double getBalance(){
        return this.balance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
