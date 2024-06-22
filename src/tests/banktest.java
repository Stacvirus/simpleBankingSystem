package tests;

import account.Account;
import account.domain.BusinessAccount;
import account.domain.SavingsAccount;
import bank.Bank;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class banktest {
    private Account user;
    private SavingsAccount savingUser;
    private BusinessAccount businessUser;
    private Bank bank;

    @Before
    public void init() throws Exception{
        this.user = new Account("stac");
        this.savingUser = new SavingsAccount("fucker");
        this.businessUser = new BusinessAccount("business man");

        this.bank = new Bank("bankTest");

        bank.deleteBankTable("transactions");
        bank.deleteBankTable("accounts");

    }
    @Test
    public void createABank()throws Exception{
        System.out.println("in create bank test!");
        Assert.assertEquals(0, bank.getNumberOfAccounts("accounts"));
    }

    @Test
    public void addAndAccountToTheBank()throws Exception{
        bank.createAccountTable();
        bank.addAccount(user);
        bank.addAccount(savingUser);
        bank.addAccount(businessUser);
        Assert.assertEquals(3, bank.getNumberOfAccounts("accounts"));
    }

    @Test
    public void removeAccountFromTheBank()throws Exception{
        bank.createAccountTable();
        bank.addAccount(user);
        bank.addAccount(savingUser);
        bank.addAccount(businessUser);

        bank.deleteAccount(user.getHolderName());
        Assert.assertEquals(2, bank.getNumberOfAccounts("accounts"));
    }

    @Test
    public void getAccountByNumberFromTheBank(){
        bank.createAccountTable();
        bank.addAccount(user);
        bank.addAccount(savingUser);
        bank.addAccount(businessUser);

        Account account = bank.getAccountByTarget(String.valueOf(businessUser.getNumber()), "number");
        assertEquals("business man", account.getHolderName());
    }

    @Test
    public void accountDoNotExistInBank(){
        bank.createAccountTable();
        bank.addAccount(savingUser);
        bank.addAccount(businessUser);
        assertNull(bank.getAccountByTarget(String.valueOf(user.getNumber()), "number"));
    }

    @Test
    public void getAccountByNameFromTheBank()throws Exception{
        bank.createAccountTable();
        this.user.addBank(bank);
        this.savingUser.addBank(bank);

        Assert.assertEquals(500.0, bank.getAccountByTarget(savingUser.getHolderName(),"name").getBalance(), 0.0);
    }
}
