package com.team5.HAPark.Order.DAO;

import com.team5.HAPark.Order.IItem;
import com.team5.HAPark.Order.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IOrderPersistence {
    void saveOrder(Order order) throws SQLException;
    void saveOrderItem(int orderId, String itemId, int quantity) throws SQLException;
    Order loadOrder(int orderId) throws SQLException;
    Map<IItem,Integer> loadOrderItems(int orderId) throws SQLException;
    List<Order> loadAllOrders(String email) throws SQLException;
}