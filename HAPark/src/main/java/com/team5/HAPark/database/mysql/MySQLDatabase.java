package com.team5.HAPark.database.mysql;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.*;

@ComponentScan
@Component
public class MySQLDatabase implements IDataBase{

    private ResultSet resultSet;
    private Connection conn;
    private Statement statement;
    private static MySQLDatabase database;

    private MySQLDatabase(){}

    public static MySQLDatabase getInstance(){
      if (database == null){
          database = new MySQLDatabase();
      }
      return database;
    }

    @Override
    public void connect() {

        if (conn != null){
            close();
        }

        String user = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");
        String dbName = System.getenv("DB_NAME");

        if (user == null){
            user = "CSCI5308_5_DEVINT_USER";
        }

        if (password == null){
            password = "ozeeVa1EiD3Ohfa5";
        }

        if (dbName == null){
            dbName = "CSCI5308_5_DEVINT";
        }

        String connectionURL = "jdbc:mysql://db-5308.cs.dal.ca:3306/" + dbName;

        try {
            conn = DriverManager.getConnection(connectionURL, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()){
            connect();
        }
        return conn;
    }

    @Override
    public void close() {

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
