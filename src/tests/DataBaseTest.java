package tests;

import dataBase.DataBase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataBaseTest {
    private DataBase db;
    @Before
    public void init(){
        this.db = new DataBase("data");

        this.db.deleteTable("accounts");
        this.db.deleteTable("transactions");
    }

    @Test
    public void dummy(){
        System.out.println("test that eraces the db!");
    }

    @Test
    public void testToCreateAccountTable(){
        boolean ans = db.AccountTable();
        assertTrue(ans);
    }

    @Test
    public void testToCreateTransactionTable(){
        boolean ans = db.TransactionTable();
        assertTrue(ans);
    }

    @Test
    public void insertAndSelectAccountData()throws Exception{
        db.AccountTable();
        db.insertAccount("stac", 45.75, 123654789);
        assertEquals("45.75", db.selectAccount("balance", "stac"));
    }

    @Test
    public void updateAccountData()throws Exception{
        db.AccountTable();
        db.insertAccount("stac", 45.75, 123654789);
        db.updateAccountData(50.55, 123654789);
        assertEquals("50.55", db.selectAccount("balance", "stac"));
    }

    @Test
    public void insertAndSelectTransationData()throws Exception{
        db.TransactionTable();
        db.insertTransaction("stac", -45.75);
        assertEquals("withdraw: 45.75, ", db.selectTransaction("stac"));
    }

    @Test
    public void countTheNumberOfAccounts(){
        db.AccountTable();
        db.insertAccount("stac", 525,456123);
        db.insertAccount("stacfucker", 525,46123);
        db.insertAccount("stacvirus", 525,45613);

        assertEquals(3, db.countAccounts("accounts"));
    }
}
