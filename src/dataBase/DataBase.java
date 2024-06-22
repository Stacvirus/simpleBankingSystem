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

    public String selectAccount(String target){
        return bank.selectAccountData(target);

    }

    public String selectTransaction(String target){
        return bank.selectTransactionsData(target);
    }

    public boolean updateAccountData(double balance, int number){
        return bank.updateAccountData(balance, number);
    }

    public int countAccounts(String target)throws Exception{
        ResultSet res = bank.numberOfElements(target);
        if(res.next()){
            return res.getInt("rowcount");
        }
        res.close();
        return 0;
    }

    public void removeAccount(String name){
        bank.deleteAccount(name);
    }

    public String getAccount(String number, String target){
        return bank.selectAccountByNumber(number, target);
    }
}
