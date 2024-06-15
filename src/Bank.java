import java.util.ArrayList;
import java.util.HashMap;

public class Bank {
    private HashMap<Integer, Account> accounts;

    public Bank(){
        this.accounts = new HashMap();
    }

    public void addAccount(Account account){
        this.accounts.put(account.getNumber(), account);
        account.addBank(this);
    }

    public Account getAccountByNumber(Account account){
        if(this.accounts.get(account.getNumber()) == null){
            return null;
        }
        return this.accounts.get(account.getNumber());
    }

    public int numberOfAccounts(){
        return this.accounts.size();
    }

    public void removeAccount(Account account){
        if(this.getAccountByNumber(account) != null){
            this.accounts.remove(account.getNumber());
        }
    }

    public Account getAccountByName(String name){
        Account account = null;
        for(Account act : this.accounts.values()){
            if(act.getHolderName().equals(name)){
                account = act;
                break;
            }
        }
        return account;
    }

}
