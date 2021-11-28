package com.team5.HAPark.database.mysql;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ComponentScan
@Component
public class MySQLDatabase implements IDataBase{

    private ResultSet resultSet;
    private Connection conn;
    private Statement statement;

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

    public Connection getConnection(){
        if (conn == null){
            connect();
        }
        return conn;
    }

    @Override
    public List<Map<String, Object>> query(String query) {
        List<Map<String, Object>> rows = new ArrayList<>();

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            rows = convertResultSetToListOfMaps(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
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

    public List<Map<String, Object>> convertResultSetToListOfMaps(ResultSet rs) throws SQLException {

        List<Map<String, Object>> rows = new ArrayList<>();
        ResultSetMetaData metaData = rs.getMetaData();
        int col = metaData.getColumnCount();

        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i<=col; i++) {
                String key = metaData.getColumnName(i);
                Object value = rs.getObject(i);
                row.put(key,value);
            }
            rows.add(row);
        }

        return rows;

    }

}
