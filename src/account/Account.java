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
        this.fileName = "transactions.txt";

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

    private List<String> isExist(String name)throws Exception{
        int index = 1;
        List<String> ans = new ArrayList<>();
        List<String> accounts = Files.readAllLines(Paths.get(fileName));

        for(String account: accounts){
            String[] res = account.split(":");

            if(res[0].equals(name)){
                ans.add(account);
                ans.add(String.valueOf(index));
                return ans;
            }
        }
        return null;
    }

    private void saveToFile(double data)throws Exception{
        StringBuilder dataToSave = new StringBuilder();
        if(data > 0){
            dataToSave.append("deposit= ").append(data).append(", ");
        }else {
            dataToSave.append("withdraw= ").append(data * -1).append(", ");
        }

        try(FileWriter writer = new FileWriter(fileName, true)){
            List<String> res = this.isExist(this.holderName);
            if(res == null){
                writer.write(this.holderName+": "+dataToSave);
            } else{
                List<String> transactions = Files.readAllLines(Paths.get(fileName));

                String result = res.get(0) + dataToSave;
                transactions.set(Integer.parseInt(res.get(1)) - 1, result);

                Files.write(Paths.get(fileName), transactions);
            }
        }catch(Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public void deposit(double amount)throws Exception{
        System.out.println("trying to deposit money!");
        this.balance += amount;
        try {
            this.bank.updateAccount(this.holderName, this.balance);
        }catch (Exception e){
            System.out.println("Error: impossible to update user account data, "+e.getMessage());
        }
        this.saveToFile(amount);
    }

    public boolean withdraw(double amount)throws Exception{
        if(this.balance > 0){
            this.balance -= amount;
            this.saveToFile(amount * -1);

            try {
                System.out.println("trying to withdraw!");
                this.bank.updateAccount(this.holderName, this.balance);
            }catch (Exception e){
                System.out.println("Error: impossible to update user account data, "+e.getMessage());
            }
            return true;
        }
        return false;
    }

    public int getNumber(){
        return this.number;
    }

    public boolean printHistory()throws Exception{
        System.out.println(this.holderName+" account's transaction history: " +
                "\n------------------------------");
        StringBuilder ans = new StringBuilder();
        List<String> res = Files.readAllLines(Paths.get(fileName));

        for(String transactions: res){
            String[] datas = transactions.split(":");
            if(datas[0].equals(this.holderName)){
                for(String history:datas[1].split(",")){
                    ans.append(history).append("\n");
                }
            }
        }

        if(ans.length() == 0){
            ans.append("no transactions for the moment!\n");
        }
        System.out.println(ans+"----------------------------------");
        return true;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean deductFees(double fees){
        if(this.balance > 0){
            this.balance -= fees;
            return true;
        }
        return false;
    }
}
