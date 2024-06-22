package tests;

import account.Account;
import account.domain.SavingsAccount;
import bank.Bank;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountTest {
    private Account user;
    private Bank bank;
    @Before
    public void init()throws Exception{
        this.user = new Account("stac virus");
        this.bank = new Bank("data.txt");
//        try(PrintWriter writer = new PrintWriter("transactions.txt")){
//            System.out.println("clear successfull!");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
//
//        try(PrintWriter writer = new PrintWriter("data.txt")){
//            System.out.println("clear successfull!");
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//        }
    }

    @Test
    public void createAccount(){
        assertEquals("stac virus", user.getHolderName());
    }

    @Test
    public void getUserBalance(){
        assertEquals(0.0, user.getBalance(), 0.0);
    }

    @Test
    public void userDepositesMoney()throws Exception{
        user.addBank(bank);
        user.deposit(200);
        assertEquals(200.0, user.getBalance(), 0.0);
    }

    @Test
    public void printAccountTransactionHistory()throws Exception{
        user.addBank(bank);
        user.deposit(500);
        user.withdraw(125);
        user.withdraw(50);
        user.deposit(325);

        assertTrue(user.printHistory());
    }

    @Test
    public void userWithdrawMoney()throws Exception{
        user.addBank(bank);
        user.deposit(200);
        user.withdraw(150);
        assertEquals(50, user.getBalance(), 0.0);
    }

    @Test
    public void savingAccountCreated(){
        SavingsAccount savingsAccount = new SavingsAccount("stac");
        assertEquals(500, savingsAccount.getBalance(), 0.0);
    }

    @Test
    public void getUserAndDoDeposit()throws Exception{
        Account user = this.bank.getAccountByName("stac");
        double oldBalance = user.getBalance();
        user.deposit(50);
        assertEquals(oldBalance+50, user.getBalance(),0.0);
    }

    @Test
    public void updateUserAccountData()throws Exception{
        Account user = this.bank.getAccountByName("stac");
        //this.bank.updateAccount(user.getHolderName(), 500);
        user.deposit(400);
        assertEquals(900, user.getBalance(), 0.0);

    }

}