package com.team5.HAPark.Order;

import com.team5.HAPark.Food.DAO.MySQLFoodPersistence;
import com.team5.HAPark.Food.FoodService;
import com.team5.HAPark.Order.DAO.IOrderPersistence;
import com.team5.HAPark.Order.DAO.MySQLFoodOrderPersistence;
import database.mysql.MySQLDatabase;

public class FoodOrderFactory implements IOrderFactory{

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
        return new MySQLFoodOrderPersistence(database,new FoodService(new MySQLFoodPersistence(database)));
    }
}
