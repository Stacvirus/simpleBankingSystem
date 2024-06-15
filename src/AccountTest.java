import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {
    private Account user;
    @Before
    public void init(){
        this.user = new Account("stac virus");
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
    public void userDepositesMoney(){
        user.deposite(200);
        assertEquals(200.0, user.getBalance(), 0.0);
    }

    @Test
    public void userWithdrawMoney(){
        user.deposite(200);
        user.withdraw(150);
        assertEquals(50, user.getBalance(), 0.0);
    }

}
