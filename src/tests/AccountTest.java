package tests;

import account.Account;
import account.domain.BusinessAccount;
import account.domain.SavingsAccount;
import bank.Bank;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccountTest {
    private Account user;
    private SavingsAccount savingUser;
    private BusinessAccount businessUser;
    private Bank bank;
    @Before
    public void init()throws Exception{
        this.user = new Account("stac virus");
        this.savingUser = new SavingsAccount("fucker");
        this.businessUser = new BusinessAccount("business man");

        this.bank = new Bank("bankTest");

        bank.deleteBankTable("transactions");
        bank.deleteBankTable("accounts");
    }

    @Test
    public void createAccount(){
        assertEquals("stac virus", user.getHolderName());
    }

    @Test
    public void getUserBalance(){
        bank.createAccountTable();
        savingUser.addBank(bank);
        assertEquals(500.0, savingUser.getBalance(), 0.0);
    }

    @Test
    public void userDepositesMoney(){
        bank.createAccountTable();
        savingUser.addBank(bank);
        assertTrue(savingUser.deposit(50));
        assertEquals(550.0, savingUser.getBalance(), 0.0);
    }
    @Test
    public void userWithdrawsMoney(){
        bank.createAccountTable();
        savingUser.addBank(bank);
        assertTrue(savingUser.withdraw(50));
        assertEquals(450.0, savingUser.getBalance(), 0.0);
    }

    @Test
    public void printAccountTransactionHistory(){
        bank.createAccountTable();
        user.addBank(bank);
        user.deposit(500);
        user.withdraw(125);
        user.withdraw(50);
        user.deposit(325);

        System.out.println(user.toHistory());
        assertEquals((500-125-50)+325*1.0,user.getBalance(), 0.0);
    }


    @Test
    public void getUserAndDoDeposit(){
        bank.createAccountTable();
        user.addBank(bank);
        Account user = this.bank.getAccountByTarget("stac virus", "name");
        double oldBalance = user.getBalance();

        user.deposit(50);
        assertEquals(oldBalance+50, user.getBalance(),0.0);
    }

    @Test
    public void getUserAndWithdraw()throws Exception{
        bank.createAccountTable();
        user.addBank(bank);
        Account user = this.bank.getAccountByTarget("stac virus", "name");
        user.deposit(400);
        assertEquals(400, user.getBalance(), 0.0);
    }
}
