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

    public String selectAccount(String target)throws Exception{
        List<String> ans = new ArrayList<>();
        ResultSet res = bank.selectAccountData(target);
        while(res.next()){
            ans.add(String.valueOf(res.getDouble(target)));
        }
        res.close();
        return ans.toString();
    }

    public String selectTransaction(String target)throws Exception{
        StringBuilder ans = new StringBuilder();
        ResultSet res = bank.selectTransactionsData(target);
        while(res.next()){
            double amount = res.getDouble("amount");
            if(amount > 0) {
                ans.append("deposit: ").append(amount).append(", ");
            } else {
                ans.append("withdraw: ").append(amount * -1).append(", ");
            }
        }
        res.close();
        return ans.toString();
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
