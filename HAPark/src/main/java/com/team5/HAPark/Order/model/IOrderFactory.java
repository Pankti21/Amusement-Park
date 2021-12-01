package com.team5.HAPark.Order.model;

import com.team5.HAPark.Order.DAO.IOrderPersistence;
import com.team5.HAPark.database.mysql.MySQLDatabase;


public interface IOrderFactory {

    IOrderService createOrderService(IOrderPersistence orderPersistence);

    IOrder createOrder();

    IOrderItem createOrderItem();

    IOrderPersistence createOrderPersistence(MySQLDatabase database);
}
