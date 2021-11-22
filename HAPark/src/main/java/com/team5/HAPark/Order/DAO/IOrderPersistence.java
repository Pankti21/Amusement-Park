package com.team5.HAPark.Order.DAO;

import com.team5.HAPark.Order.model.IOrder;
import com.team5.HAPark.Order.model.IOrderItem;

import java.sql.SQLException;
import java.util.List;

public interface IOrderPersistence {
    void saveOrder(IOrder order) throws SQLException;
    void saveOrderItem(int orderId, String itemId, int quantity) throws SQLException;
    IOrder loadOrder(int orderId) throws SQLException;
    List<IOrderItem> loadOrderItems(int orderId) throws SQLException;
    List<IOrder> loadAllOrders(String email) throws SQLException;
}