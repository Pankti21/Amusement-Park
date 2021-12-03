package com.team5.HAPark.order;

import com.team5.HAPark.order.DAO.IOrderPersistence;
import com.team5.HAPark.order.DAO.IOrderPersistenceFactory;
import com.team5.HAPark.order.DAO.OrderPersistenceFactory;
import com.team5.HAPark.order.model.*;
import com.team5.HAPark.database.mysql.MySQLDatabase;

public class FoodOrderFactory implements IOrderFactory {

    private IOrderPersistenceFactory orderPersistenceFactory;

    public FoodOrderFactory() {
        this.orderPersistenceFactory = new OrderPersistenceFactory();
    }

    @Override
    public IOrderService createOrderService(IOrderPersistence orderPersistence) {
        return new OrderService(orderPersistence);
    }

    @Override
    public IOrder createOrder() {
        return new Order();
    }

    @Override
    public IOrderItem createOrderItem() {
        return new OrderItem();
    }

    @Override
    public IOrderPersistence createOrderPersistence(MySQLDatabase database) {
        return orderPersistenceFactory.createFoodOrderPersistence();
    }
}
