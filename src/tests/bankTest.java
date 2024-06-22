package tests;

import account.Account;
import account.domain.BusinessAccount;
import account.domain.SavingsAccount;
import bank.Bank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class bankTest {
    private Account user;
    private SavingsAccount savingUser;
    private BusinessAccount businessUser;
    private Bank bank;

    @Before
    public void init() throws Exception{
        this.user = new Account("stac");
        this.savingUser = new SavingsAccount("fucker");
        this.businessUser = new BusinessAccount("business man");

        this.bank = new Bank("data.txt");
        try(PrintWriter writer = new PrintWriter("data.txt")){
            System.out.println("cleared sucessfull!");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
    @Test
    public void createABank(){
        System.out.println("in create bank test!");
        Assert.assertEquals(0, bank.getNumberOfAccounts());
    }

    @Test
    public void addAndAccountToTheBank()throws Exception{
        bank.addAccount(user);
        Assert.assertEquals(1, bank.getNumberOfAccounts());
    }

    @Test
    public void removeAccountFromTheBank()throws Exception{
        this.user.addBank(bank);
        this.savingUser.addBank(bank);

        bank.removeAccount(savingUser.getHolderName());
        Assert.assertEquals(1, bank.getNumberOfAccounts());
    }

    @Test
    public void getAccountByNumberFromTheBank()throws Exception{
        this.user.addBank(bank);
        this.savingUser.addBank(bank);
        this.businessUser.setNumber(112233);
        this.businessUser.addBank(bank);
        Assert.assertEquals("business man", bank.getAccountByNumber(112233).getHolderName());
    }

    @Test
    public void accountDoNotExistInBank()throws Exception{
        Assert.assertEquals(null, bank.getAccountByNumber(user.getNumber()));
    }

    @Test
    public void getAccountByNameFromTheBank()throws Exception{
        this.user.addBank(bank);
        this.savingUser.addBank(bank);
        user.deposit(250);
        Assert.assertEquals(250.0, bank.getAccountByName(user.getHolderName()).getBalance(), 0.0);
    }
}
