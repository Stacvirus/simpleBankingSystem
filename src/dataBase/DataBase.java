package dataBase;

import account.Account;
import dataBase.domain.CreateTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private CreateTable bank;
    public DataBase(String dbName){
        this.bank = new CreateTable(dbName);
    }

    public boolean AccountTable(){
        return bank.createAccountTable();
    }

    public boolean TransactionTable(){
        return bank.createTransactionTable();
    }

    public void deleteTable(String name){
        bank.removeTable(name);
    }

    public boolean insertAccount(String name, double balance, int num){
        return bank.insertAccountData(name,balance, num);
    }

    public void insertTransaction(String name, double amount){
        bank.insertTransactionData(name, amount);
    }

    public String selectAccount(String target, String name){
        return bank.selectAccountData(target, name);
    }

    public String selectTransaction(String target){
        return bank.selectTransactionsData(target);
    }

    public boolean updateAccountData(double balance, int number){
        return bank.updateAccountData(balance, number);
    }

    public int countAccounts(String target){
        return bank.numberOfElements(target);
    }

    public void removeAccount(String name){
        bank.deleteAccount(name);
    }

    public String getAccount(String number, String target){
        return bank.selectAccountByNumber(number, target);
    }

    public void createNewTables(String name){
        if(!bank.tableExist(name)){
            this.AccountTable();
            this.TransactionTable();
        }
    }

}
