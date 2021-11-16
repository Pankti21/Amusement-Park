package com.team5.HAPark.Order.DAO;

import com.team5.HAPark.Order.IOrder;

import java.util.List;

public interface IOrderPersistence {
    void saveOrder(IOrder order);
    IOrder loadOrder(String orderId);
    List<IOrder> loadAllOrders(String email);
}