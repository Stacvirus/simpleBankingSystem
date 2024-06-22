package dataBase;

import dataBase.domain.CreateTable;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private CreateTable bank;
    public Main(String dbName){
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

    public void insertAccount(String name, double balance, int num){
        bank.insertAccountData(name,balance, num);
    }

    public String selectAccount()throws Exception{
        List<String> ans = new ArrayList<>();
        ResultSet res = bank.selectAccountData();
        while(res.next()){
            ans.add(res.getString("name"));
        }
        res.close();
        return ans.get(0);
    }
}
