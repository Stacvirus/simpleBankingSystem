package bank;

import account.Account;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Bank {
    private int accountNum;
    private Scanner reader;
    private String fileName;

    public Bank(String fileName)throws Exception{
        this.accountNum = 0;
        this.fileName = fileName;
        this.reader = new Scanner(Paths.get(fileName));
    }

    public boolean isExist(String name)throws Exception{
        List<String> accounts = this.getAllAccounts();
        for(String account: accounts){
            String[] res = account.split(",");
            if(res[0].equals(name)){
                return true;
            }
        }
        return false;
    }

    public boolean addAccount(Account account)throws Exception{
        if(this.isExist(account.getHolderName())){
            return false;
        }

        try(FileWriter saver = new FileWriter(fileName, true)){

            StringBuilder text = new StringBuilder(account.getHolderName());

            text.append(",").append(account.getBalance()).append(",").append(account.getNumber());
            System.out.println(text);

            saver.write(String.valueOf(text));
            saver.write(System.lineSeparator());
            //this.accountNum++;
            return true;
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
            return false;
        }
    }

    public int getNumberOfAccounts() {
        try{
            this.reader = new Scanner(Paths.get(fileName));

            while (reader.hasNextLine()){
                if(!reader.nextLine().isEmpty()) {
                    this.accountNum++;
                }
            }
        }catch (Exception e){
            System.out.println("imposible to read file!!");
        }

        return accountNum;
    }

    public Account getAccountByNumber(int accountNumber)throws Exception{
        List<String> accounts = this.getAllAccounts();

        for(String account:accounts){
            String[] res = account.split(",");
            if(Integer.parseInt(res[2]) == accountNumber){
                return this.createAccount(res);
            }
        }
        return null;
    }

    public void removeAccount(String name)throws Exception{
        int lineToRemove = 1;
        List<String> lines = this.getAllAccounts();

        while (reader.hasNextLine()){
            String[] accountInfos = reader.nextLine().split(",");
            if(accountInfos[0].equals(name)){
                break;
            }
            lineToRemove++;
        }
        lines.remove(lineToRemove - 1);
        Files.write(Paths.get(fileName), lines);
    }

    public Account getAccountByName(String name)throws Exception{
        List<String> accounts = this.getAllAccounts();

        for(String account:accounts){
            String[] res = account.split(",");
            if(res[0].equals(name)){
                return this.createAccount(res);
            }
        }
        return null;
    }

    public Account createAccount(String[] res){
        Account findAccount = new Account(res[0]);
        findAccount.setBalance(Double.parseDouble(res[1]));
        findAccount.setNumber(Integer.parseInt(res[2]));
        return findAccount;
    }

    private List<String> getAllAccounts()throws Exception{
        return Files.readAllLines(Paths.get(fileName));
    }

    public void updateAccount(String name, double balance){
        System.out.println("in update account method!");
        try {
            List<String> accounts = this.getAllAccounts();

            String wantedLine = "";
            int index = 1;
            for(String data : accounts){
                if(data.split(",")[0].equals(name)){
                    wantedLine = data;
                    break;
                }
                index++;
            }
            System.out.println("wanted line: "+wantedLine);

            String[] temp = wantedLine.split(",");
            temp[1] = String.valueOf(balance);
            StringBuilder finalResult = new StringBuilder(temp[0]);
            finalResult.append(",").append(temp[1]).append(",").append(temp[2]);
            System.out.println(finalResult);
            accounts.set(index - 1, String.valueOf(finalResult));
            Files.write(Paths.get(fileName), accounts);
        }catch (Exception e){
            System.out.println("Error: impossible to get all data, "+e.getMessage());
        }

    }
}
