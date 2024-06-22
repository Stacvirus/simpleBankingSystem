package account.domain;

import account.Account;

public class BusinessAccount extends Account {
    public double fees;

    public BusinessAccount(String name){
        super(name);
        this.fees = 5;
    }

    public boolean deduceFees(){
        return super.deductFees(this.fees);
    }

    public void print(){
        System.out.println(super.toString()
                +"\nFees: "
                +this.fees
                +"\n----------");
    }
}
