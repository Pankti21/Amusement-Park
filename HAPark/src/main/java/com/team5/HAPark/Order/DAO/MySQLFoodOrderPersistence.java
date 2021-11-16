package com.team5.HAPark.Order.DAO;

import com.team5.HAPark.Food.Food;
import com.team5.HAPark.Food.FoodService;
import com.team5.HAPark.Order.IItem;
import com.team5.HAPark.Order.Order;
import database.mysql.MySQLDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySQLFoodOrderPersistence implements IOrderPersistence{

    private MySQLDatabase mySQLDatabase;
    private FoodService foodService;

    public MySQLFoodOrderPersistence(MySQLDatabase mySQLDatabase, FoodService foodService){
        this.mySQLDatabase = mySQLDatabase;
        this.foodService = foodService;
    }

    @Override
    public void saveOrder(Order order) throws SQLException {

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

            Map<IItem,Integer> orderItemQuantities = order.getOrderItemQuantities();
            for (Map.Entry<IItem,Integer> entry: orderItemQuantities.entrySet()){

                IItem item = entry.getKey();
                int quantity = entry.getValue();

                saveOrderItem(orderId,item.getId(),quantity);
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
    public void saveOrderItem(int orderID, String itemId, int quantity) throws SQLException {

        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call save_food_order_item(?,?,?)} ");

            statement.setInt(1,orderID);
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
    public Order loadOrder(int orderId) throws SQLException {

        Order order = null;
        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call load_food_order(?,?,?,?)} ");

            statement.setInt(1,orderId);
            statement.registerOutParameter(2,Types.DATE);
            statement.registerOutParameter(3,Types.TIME);
            statement.registerOutParameter(4,Types.VARCHAR);

            statement.execute();

            order = new Order();

            order.setOrderId(orderId);
            order.setOrderDate(statement.getDate(2).toLocalDate());
            order.setOrderTime(statement.getTime(3).toLocalTime());
            order.setMailId(statement.getString(4));

            Map<IItem,Integer> itemQuantities = loadOrderItems(orderId);
            order.setOrderItemQuantities(itemQuantities);

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

    @Override
    public Map<IItem,Integer> loadOrderItems(int orderId) throws SQLException {

        Map<IItem,Integer> orderItemQuantities = new HashMap<>();

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

                orderItemQuantities.put(food,quantity);
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

        return orderItemQuantities;
    }

    @Override
    public List<Order> loadAllOrders(String email) throws SQLException {

        List<Order> orders = new ArrayList<>();
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
                Map<IItem,Integer> itemQuantities = loadOrderItems(orderId);

                Order order = new Order();

                order.setOrderId(orderId);
                order.setMailId(email);
                order.setOrderDate(date);
                order.setOrderTime(time);
                order.setOrderItemQuantities(itemQuantities);

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
