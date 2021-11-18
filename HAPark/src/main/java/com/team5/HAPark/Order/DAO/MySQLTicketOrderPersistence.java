package com.team5.HAPark.Order.DAO;

import com.team5.HAPark.Order.TicketOrder;
import com.team5.HAPark.Ticket.Ticket;
import com.team5.HAPark.Ticket.TicketOrderItem;
import com.team5.HAPark.Ticket.TicketService;
import database.mysql.MySQLDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MySQLTicketOrderPersistence implements ITicketOrderPersistence{

    private MySQLDatabase mySQLDatabase;
    private TicketService ticketService;

    public MySQLTicketOrderPersistence(MySQLDatabase mySQLDatabase, TicketService ticketService) {
        this.mySQLDatabase = mySQLDatabase;
        this.ticketService = ticketService;
    }

    @Override
    public void saveOrder(TicketOrder order) throws SQLException {

        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call save_ticket_order(?,?,?,?)} ");

            statement.setString(1,order.getMailId());
            statement.setDate(2, Date.valueOf(order.getOrderDate()));
            statement.setTime(3, Time.valueOf(order.getOrderTime()));
            statement.registerOutParameter(4, Types.INTEGER);

            statement.execute();

            int orderId = statement.getInt(4);
            order.setOrderId(orderId);

            List<TicketOrderItem> orderItems = order.getOrderItems();
            for (TicketOrderItem ticketOrderItem: orderItems){

                String itemID = ticketOrderItem.getTicketType();
                int quantity = ticketOrderItem.getQuantity();

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
    public void saveOrderItem(int orderId, String ticketType, int quantity) throws SQLException {

        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call save_ticket_order_item(?,?)} ");

            statement.setInt(1,orderId);
            statement.setString(2, ticketType);

            for (int i = 0; i<quantity; i++) {
                statement.execute();
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
    public TicketOrder loadOrder(int orderId) throws SQLException {

        TicketOrder order;
        CallableStatement statement = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call load_Ticket_order(?,?,?,?)} ");

            statement.setInt(1,orderId);
            statement.registerOutParameter(2,Types.DATE);
            statement.registerOutParameter(3,Types.TIME);
            statement.registerOutParameter(4,Types.VARCHAR);

            statement.execute();

            order = new TicketOrder();

            order.setOrderId(orderId);
            order.setOrderDate(statement.getDate(2).toLocalDate());
            order.setOrderTime(statement.getTime(3).toLocalTime());
            order.setMailId(statement.getString(4));

            List<TicketOrderItem> orderItems = loadOrderItems(orderId);
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

    @Override
    public List<TicketOrderItem> loadOrderItems(int orderId) throws SQLException {

        List<TicketOrderItem> orderItems = new ArrayList<>();

        CallableStatement statement = null;
        ResultSet rs = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call load_ticket_order_items(?)} ");

            statement.setInt(1,orderId);
            statement.execute();

            rs = statement.getResultSet();

            while (rs.next()) {

                int quantity = rs.getInt("quantity");
                String ticketType = rs.getString("ticket_type");
                Ticket ticket = ticketService.getTicket(ticketType);

                orderItems.add(new TicketOrderItem(ticket,quantity));
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
    public List<TicketOrder> loadAllOrders(String email) throws SQLException {

        List<TicketOrder> orders = new ArrayList<>();
        CallableStatement statement = null;
        ResultSet rs = null;

        try {

            statement = mySQLDatabase.getConnection().prepareCall("{call load_ticket_orders_for_user(?)} ");

            statement.setString(1,email);
            statement.execute();

            rs = statement.getResultSet();

            while (rs.next()) {

                int orderId = rs.getInt("ticket_order_id");
                LocalDate date = rs.getDate("order_date").toLocalDate();
                LocalTime time = rs.getTime("order_time").toLocalTime();
                List<TicketOrderItem> orderItems = loadOrderItems(orderId);

                TicketOrder order = new TicketOrder();

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
