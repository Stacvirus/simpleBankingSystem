public class SavingsAccount extends Account {
    public double fees;

    public SavingsAccount(String name){
        super(name, 500);
        this.fees = 2;
    }

    public boolean deduceFees(){
        return super.deductFees(this.fees);
    }
}
