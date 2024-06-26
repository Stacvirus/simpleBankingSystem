package dataBase.domain;

import java.sql.*;

public class CreateTable {
    private Statement statement;
    private Connection connection;
    private ResultSet result;
    private PreparedStatement preparedStatement;

    public CreateTable(String dbName){
        try{
            Class.forName("org.sqlite.JDBC");
            this.connection = DriverManager.getConnection("jdbc:sqlite:"+dbName+".db");
            System.out.println("connection to data base establish");
            this.statement = connection.createStatement();
            this.preparedStatement = null;
        }catch (Exception e){
            System.out.println("Error: cannot connect to db, "+e.getMessage());
        }
    }

    public boolean createAccountTable(){
        String createTable = "CREATE TABLE accounts ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "name TEXT NOT NULL,"+
                "balance DOUBLE,"+
                "number INTEGER NOT NULL)";
        try {
            statement.execute(createTable);
            return true;
        }catch (Exception e){
            System.out.println("Error: impossible to create table, "+e.getMessage());
            return false;
        }
    }

    public boolean createTransactionTable(){
        String createTable = "CREATE TABLE transactions ("+
                "name TEXT NOT NULL,"+
                "amount DOUBLE)";
        try {
            statement.execute(createTable);
            return true;
        }catch (Exception e){
            System.out.println("Error: impossible to create table, "+e.getMessage());
            return false;
        }
    }

    public void removeTable(String tableName){
        try{
            statement.executeUpdate("DROP TABLE IF EXISTS "+tableName);
            System.out.println("table deletion complete!");
        }catch (Exception e){
            System.out.println("Error: "+e.getMessage());
        }
    }

    public boolean isExist(int number){
        try{
            preparedStatement = connection.prepareStatement("SELECT 1 FROM accounts WHERE number = ?");
            preparedStatement.setInt(1, number);
            this.result = preparedStatement.executeQuery();
            return result.next();
        }catch (Exception e){
            System.out.println("Error: impossible to check the existence on the row, "+e.getMessage());
        }
        return false;
    }

    public boolean insertAccountData(String name, double balance, int number){
        try{
            if(isExist(number)){
                return false;
            }
            String query = "INSERT INTO accounts (name, balance, number) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, balance);
            preparedStatement.setInt(3, number);
            preparedStatement.executeUpdate();
            System.out.println("inserting account successfull!");
            return true;
        }catch(Exception e){
            System.out.println("Error: impossible to insert, "+e.getMessage());
            return false;
        }
    }

    public void insertTransactionData(String name, double amount){
        try{
            String query = "INSERT INTO transactions (name, amount) VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, amount);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println("impossible to insert data in the transaction table, "+e.getMessage());
        }
    }

    public String selectAccountData(String target, String name){

        try {
            StringBuilder ans = new StringBuilder();
            preparedStatement = connection.prepareStatement("SELECT "+target+" from accounts WHERE name = ?");
            preparedStatement.setString(1, name);
            this.result = preparedStatement.executeQuery();

            while(result.next()){
                ans.append(result.getDouble(target));
            }
            result.close();
            return ans.toString();
        }catch (Exception e){
            System.out.println("Error: impossible to select row, "+e.getMessage());
            return null;
        }
    }

    public String selectTransactionsData(String target){
        try {
            String query = "SELECT amount from transactions WHERE name = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, target);
            this.result = preparedStatement.executeQuery();

            StringBuilder ans = new StringBuilder();
            while(result.next()){
                double amount = result.getDouble("amount");
                if(amount > 0) {
                    ans.append("deposit: ").append(amount).append(", ");
                } else {
                    ans.append("withdraw: ").append(amount * -1).append(", ");
                }
            }
            result.close();
            return ans.toString();
        }catch (Exception e){
            System.out.println("Error: impossible to select row, "+e.getMessage());
            return null;
        }
    }

    public boolean updateAccountData(double balance, int number){
        try{
            String query = "UPDATE accounts SET balance = "+balance+" WHERE number = "+number;
            statement.execute(query);
            return true;
        }catch (Exception e){
            System.out.println("Error: impossible to update data, "+e.getMessage());
            return false;
        }
    }

    public int numberOfElements(String target){
        try{
            this.result = statement.executeQuery("SELECT COUNT(*) AS rowcount FROM "+target);
            if(result.next()){
                return result.getInt("rowcount");
            }
            result.close();
        }catch (Exception e){
            System.out.println("Error: impossible to get the table size, "+e.getMessage());
            return 0;
        }
        return 0;
    }

    public void deleteAccount(String name){
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM accounts WHERE name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM transactions WHERE name = ?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();

        }catch (Exception e){
            System.out.println("Error: impossible to delete data, "+e.getMessage());
        }
    }

    public  String selectAccountByNumber(String name, String row){

        int number = 0;
        if(row.equals("number")){
            number = Integer.parseInt(name);
        }
        try{
            preparedStatement = connection.prepareStatement("SELECT name, balance, number FROM accounts WHERE "+row+" = ?");

            if(number == 0){
                preparedStatement.setString(1, name);
            }else {
                preparedStatement.setInt(1, number);
            }
            this.result = preparedStatement.executeQuery();
            StringBuilder ans = new StringBuilder();
            while (result.next()){
                ans.append(result.getString("name")).append(",");
                ans.append(result.getDouble("balance")).append(",");
                ans.append(result.getInt("number"));
            }

            return ans.toString();
        }catch (Exception e){
            System.out.println("Error: impossible to get account's data, "+e.getMessage());
        }
        return null;
    }
    public boolean tableExist(String name){
        try{
            preparedStatement = connection.prepareStatement("SELECT name FROM sqlite_master WHERE type='table' AND name = ?");
            preparedStatement.setString(1, name);
            this.result = preparedStatement.executeQuery();
            return result.next();
        }catch (Exception e){
            System.out.println("Error: impossible to check table existence, "+e.getMessage());
        }
        return false;
    }

}
