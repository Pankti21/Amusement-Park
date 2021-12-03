package com.team5.HAPark.order.model;

import com.team5.HAPark.order.DAO.IOrderPersistence;
import com.team5.HAPark.database.mysql.MySQLDatabase;


public interface IOrderFactory {

    IOrderService createOrderService(IOrderPersistence orderPersistence);

    IOrder createOrder();

    IOrderItem createOrderItem();

    IOrderPersistence createOrderPersistence(MySQLDatabase database);
}
