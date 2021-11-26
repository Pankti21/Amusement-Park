package com.team5.HAPark.Order.model;

import com.team5.HAPark.Order.DAO.IOrderPersistence;
import com.team5.HAPark.database.mysql.MySQLDatabase;


public interface IOrderFactory {
    public IOrderService createOrderService(IOrderPersistence orderPersistence);
    public IOrder createOrder();
    public IOrderItem createOrderItem();
    public IOrderPersistence createOrderPersistence(MySQLDatabase database);
}
