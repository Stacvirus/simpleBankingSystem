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

    public void insertAccountData(String name, double balance, int number){
        try{
            String query = "INSERT INTO accounts (name, balance, number) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, balance);
            preparedStatement.setInt(3, number);
            preparedStatement.executeUpdate();
        }catch(Exception e){
            System.out.println("Error: impossible to insert, "+e.getMessage());
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

    public ResultSet selectAccountData(String target){
        try {
            this.result = statement.executeQuery("SELECT "+target+" from accounts");
            return result;
        }catch (Exception e){
            System.out.println("Error: impossible to select row, "+e.getMessage());
            return null;
        }
    }

    public ResultSet selectTransactionsData(String target){
        try {
            String query = "SELECT amount from transactions WHERE name = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, target);
            this.result = preparedStatement.executeQuery();
            return result;
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




}
