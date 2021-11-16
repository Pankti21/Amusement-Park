package com.team5.HAPark.Order;

import com.team5.HAPark.Order.DAO.IOrderPersistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderService {

    private IOrderPersistence orderPersistence;

    public OrderService(IOrderPersistence orderPersistence){
        this.orderPersistence = orderPersistence;
    }

    public Order createOrderFromItemQuantities(String userId, Map<IItem,Integer> items){

        Order order = null;

        if (items != null && !items.isEmpty()){
            order = new Order();
            order.setOrderDate(LocalDate.now());
            order.setOrderTime(LocalTime.now());
            order.setMailId(userId);
            order.setOrderItemQuantities(items);
        }

        return order;
    }

    public void saveOrder(Order order){
        try {
            orderPersistence.saveOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Order getOrder(int orderId){

        Order order = null;

        try {
            order = orderPersistence.loadOrder(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public List<Order> getAllOrdersForUser(String email) throws SQLException {

        List<Order> orders = orderPersistence.loadAllOrders(email);

        if (orders == null){
            orders = new ArrayList<>();
        }

        return orders;
    }
}