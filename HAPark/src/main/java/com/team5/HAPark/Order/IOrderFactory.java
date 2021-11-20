package com.team5.HAPark.Order;

import com.team5.HAPark.Order.DAO.IOrderPersistence;
import database.mysql.MySQLDatabase;

public interface IOrderFactory {
    public IOrderService createOrderService(IOrderPersistence orderPersistence);
    public IOrder createOrder();
    public IOrderItem createOrderItem();
    public IOrderPersistence createOrderPersistence(MySQLDatabase database);
}
