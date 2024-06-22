package tests;

import dataBase.Main;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataBaseTest {
    private Main db;
    @Before
    public void init(){
        this.db = new Main("bankTest");

        this.db.deleteTable("accounts");
        this.db.deleteTable("transactions");
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
        assertEquals("stac", db.selectAccount());
    }
}
