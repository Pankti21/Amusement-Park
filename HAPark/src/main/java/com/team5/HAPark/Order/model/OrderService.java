package com.team5.HAPark.Order.model;

import com.team5.HAPark.Order.DAO.IOrderPersistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService implements IOrderService {

    private IOrderPersistence orderPersistence;

    public OrderService(IOrderPersistence orderPersistence){
        this.orderPersistence = orderPersistence;
    }

    @Override
    public IOrder createOrderFromItemQuantities(String userId, List<? extends IOrderItem> orderItems){

        IOrder order = null;

        if (orderItems != null && !orderItems.isEmpty()){
            order = new Order();
            order.setOrderDate(LocalDate.now());
            order.setOrderTime(LocalTime.now());
            order.setMailId(userId);
            order.setOrderItems(orderItems);
        }

        return order;
    }

    @Override
    public void saveOrder(IOrder order){
        try {
            orderPersistence.saveOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IOrder getOrder(int orderId){

        IOrder order = null;

        try {
            order = orderPersistence.loadOrder(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    @Override
    public List<IOrder> getAllOrdersForUser(String email) throws SQLException {

        List<IOrder> orders = orderPersistence.loadAllOrders(email);

        if (orders == null){
            orders = new ArrayList<>();
        }

        return orders;
    }
}