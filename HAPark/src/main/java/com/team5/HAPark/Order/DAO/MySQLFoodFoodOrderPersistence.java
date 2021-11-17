package com.team5.HAPark.Order.DAO;

import com.team5.HAPark.Food.Food;
import com.team5.HAPark.Food.FoodOrderItem;
import com.team5.HAPark.Food.FoodService;
import com.team5.HAPark.Order.FoodOrder;
import database.mysql.MySQLDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MySQLFoodFoodOrderPersistence implements IFoodOrderPersistence {

    private MySQLDatabase mySQLDatabase;
    private FoodService foodService;

    public MySQLFoodFoodOrderPersistence(MySQLDatabase mySQLDatabase, FoodService foodService){
        this.mySQLDatabase = mySQLDatabase;
        this.foodService = foodService;
    }

    @Override
    public void saveOrder(FoodOrder order) throws SQLException {

        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call save_food_order(?,?,?,?)} ");

            statement.setString(1,order.getMailId());
            statement.setDate(2, Date.valueOf(order.getOrderDate()));
            statement.setTime(3, Time.valueOf(order.getOrderTime()));
            statement.registerOutParameter(4, Types.INTEGER);

            statement.execute();

            int orderId = statement.getInt(4);
            order.setOrderId(orderId);

            List<FoodOrderItem> orderItems = order.getOrderItems();
            for (FoodOrderItem foodOrderItem: orderItems){

                String itemID = foodOrderItem.getId();
                int quantity = foodOrderItem.getQuantity();

                saveOrderItem(orderId,itemID,quantity);
            }

        } finally {

            try{
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveOrderItem(int orderId, String itemId, int quantity) throws SQLException {

        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call save_food_order_item(?,?,?)} ");

            statement.setInt(1,orderId);
            statement.setString(2, itemId);
            statement.setInt(3, quantity);

            statement.execute();

        } finally {

            try{
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    @Override
    public FoodOrder loadOrder(int orderId) throws SQLException {

        FoodOrder order;
        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call load_food_order(?,?,?,?)} ");

            statement.setInt(1,orderId);
            statement.registerOutParameter(2,Types.DATE);
            statement.registerOutParameter(3,Types.TIME);
            statement.registerOutParameter(4,Types.VARCHAR);

            statement.execute();

            order = new FoodOrder();

            order.setOrderId(orderId);
            order.setOrderDate(statement.getDate(2).toLocalDate());
            order.setOrderTime(statement.getTime(3).toLocalTime());
            order.setMailId(statement.getString(4));

            List<FoodOrderItem> orderItems = loadOrderItems(orderId);
            order.setOrderItems(orderItems);

        } finally {

            try{
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }

        }

        return order;
    }

    public List<FoodOrderItem> loadOrderItems(int orderId) throws SQLException {

        List<FoodOrderItem> orderItems = new ArrayList<>();

        CallableStatement statement = null;
        ResultSet rs = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call load_food_order_items(?)} ");

            statement.setInt(1,orderId);
            statement.execute();

            rs = statement.getResultSet();

            while (rs.next()) {

                int quantity = rs.getInt("quantity");
                String foodId = rs.getString("food_id");
                Food food = foodService.getFood(foodId);

                orderItems.add(new FoodOrderItem(food,quantity));
            }

        } finally {

            try{
                if (rs != null){
                    rs.close();
                }
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return orderItems;
    }

    @Override
    public List<FoodOrder> loadAllOrders(String email) throws SQLException {

        List<FoodOrder> orders = new ArrayList<>();
        CallableStatement statement = null;
        ResultSet rs = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call load_food_orders_for_user(?)} ");

            statement.setString(1,email);
            statement.execute();

            rs = statement.getResultSet();

            while (rs.next()) {

                int orderId = rs.getInt("food_order_id");
                LocalDate date = rs.getDate("order_date").toLocalDate();
                LocalTime time = rs.getTime("order_time").toLocalTime();
                List<FoodOrderItem> orderItems = loadOrderItems(orderId);

                FoodOrder order = new FoodOrder();

                order.setOrderId(orderId);
                order.setMailId(email);
                order.setOrderDate(date);
                order.setOrderTime(time);
                order.setOrderItems(orderItems);

                orders.add(order);
            }

        } finally {

            try{
                if (rs != null){
                    rs.close();
                }
                if (statement != null){
                    statement.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }

        return orders;
    }
}
