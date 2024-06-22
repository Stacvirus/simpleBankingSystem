package account.domain;

import account.Account;

public class SavingsAccount extends Account {
    public double fees;

    public SavingsAccount(String name){
        super(name, 500);
        this.fees = 2;
    }

}
