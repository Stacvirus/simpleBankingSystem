package bank;

import account.Account;
import dataBase.DataBase;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private DataBase bankDB;


    public Bank(String dataBaseName)throws Exception{
        this.bankDB = new DataBase(dataBaseName);
        bankDB.createNewTables("accounts");
    }

    public void createAccountTable(){
        bankDB.AccountTable();
        bankDB.TransactionTable();
    }

    public void deleteBankTable(String name){
        bankDB.deleteTable(name);
    }

    public void deleteAccount(String name){
        bankDB.removeAccount(name);
    }

    public String toString(){
        return "number of accounts: "+this.getNumberOfAccounts("accounts");
    }

    public String selectAccountData(String target, String name){
        System.out.println("trying to get account balance");
        return bankDB.selectAccount(target, name);
    }

    public boolean addAccount(String name, double balance, int number){
        return bankDB.insertAccount(name, balance, number);
    }

    public int getNumberOfAccounts(String target){
        return bankDB.countAccounts(target);
    }

    public Account createAccount(String accountInfos){
        String[] res = accountInfos.split(",");
        Account newAccount = new Account(res[0]);
        newAccount.setBalance(Double.parseDouble(res[1]));
        newAccount.setNumber(Integer.parseInt(res[2]));
        newAccount.addBank(this);

        return newAccount;
    }

    public Account getAccountByTarget(String targetColumn, String target){
        String ans = bankDB.getAccount(targetColumn, target);
        System.out.println(ans.isEmpty());
        if(!ans.isEmpty()){
            Account res = this.createAccount(ans);
            System.out.println("old account: "+res);
            return res;
        }
        return null;
    }

    public void setTransactionData(String name, double amount){
        bankDB.insertTransaction(name, amount);
    }

    public void updateAccountData(double amount, int accountNumber){
        bankDB.updateAccountData(amount, accountNumber);
    }

    public String getHistoryTransaction(String name){
        return bankDB.selectTransaction(name);
    }
}
