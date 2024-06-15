import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class bankTest {
    private Account user;
    private Bank bank;

    @Before
    public void init(){
        this.user = new Account("stac virus");
        this.bank = new Bank();
    }
    @Test
    public void createABank(){
        assertEquals(0, bank.numberOfAccounts());
    }

    @Test
    public void addAndAccountToTheBank(){
        bank.addAccount(user);
        assertEquals(1, bank.numberOfAccounts());
    }

    @Test
    public void removeAccountFromTheBank(){
        bank.addAccount(user);
        bank.removeAccount(user);
        assertEquals(0, bank.numberOfAccounts());
    }

    @Test
    public void getAccountByNumberFromTheBank(){
        bank.addAccount(user);
        assertEquals("stac virus", bank.getAccountByNumber(user).getHolderName());
    }

    @Test
    public void accountDoNotExistInBank(){
        assertEquals(null, bank.getAccountByNumber(user));
    }
}
