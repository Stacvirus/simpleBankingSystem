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
    }

    public void createAccountTable(){
        bankDB.AccountTable();
    }

    public void deleteBankTable(String name){
        bankDB.deleteTable(name);
    }

    public void deleteAccount(String name){
        bankDB.removeAccount(name);
    }


    public boolean addAccount(Account account){
        return bankDB.insertAccount(account.getHolderName(), account.getBalance(), account.getNumber());
    }

    public int getNumberOfAccounts(String target)throws Exception{
        return bankDB.countAccounts(target);
    }

    public Account createAccount(String accountInfos){
        String[] res = accountInfos.split(",");
        Account newAccount = new Account(res[0]);
        newAccount.setBalance(Double.parseDouble(res[1]));
        newAccount.setNumber(Integer.parseInt(res[2]));

        return newAccount;
    }

    public Account getAccountByTarget(String number, String target){
        String ans = bankDB.getAccount(number, target);
        if(ans != null){
            return this.createAccount(ans);
        }
        return null;
    }
}
