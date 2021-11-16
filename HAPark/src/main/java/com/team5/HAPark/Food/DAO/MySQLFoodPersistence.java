package com.team5.HAPark.Food.DAO;

import com.team5.HAPark.Food.Food;
import com.team5.HAPark.Food.Menu;
import database.mysql.MySQLDatabase;

import java.sql.*;

public class MySQLFoodPersistence implements IFoodPersistence{

    MySQLDatabase database;

    public MySQLFoodPersistence(MySQLDatabase database){
        this.database = database;
    }

    public Food loadFood(String id) throws SQLException {

        Food food = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = database.getConnection();

        try {

            statement = connection.createStatement();
            resultSet =  statement.executeQuery("SELECT * FROM food WHERE food_id = "+id+";");

            while(resultSet.next()){
                 String name = resultSet.getString("food_name");
                 double price = resultSet.getDouble("food_price");
                 int quantity = resultSet.getInt("food_quantity");
                 food = new Food(name,id,price,quantity);
            }

        } finally {

            try{
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return food;
    }

    public Menu loadMenu() throws SQLException {

        Menu menu = new Menu();
        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = database.getConnection();

        try {

            statement = connection.createStatement();
            resultSet =  statement.executeQuery("SELECT * FROM food;");

            while(resultSet.next()){
                String id = resultSet.getString("food_id");
                String name = resultSet.getString("food_name");
                double price = resultSet.getDouble("food_price");
                int quantiy = resultSet.getInt("food_quantity");
                Food food = new Food(name,id,price,quantiy);
                menu.addFoodToMenu(food);
            }

        } finally {

            try{
                if (resultSet != null){
                    resultSet.close();
                }
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return menu;
    }
}
