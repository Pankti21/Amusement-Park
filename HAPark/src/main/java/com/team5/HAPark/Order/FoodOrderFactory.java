package com.team5.HAPark.Order;

import com.team5.HAPark.Food.IFoodService;
import com.team5.HAPark.Order.DAO.IOrderPersistence;
import com.team5.HAPark.Order.DAO.IOrderPersistenceFactory;
import com.team5.HAPark.Order.DAO.OrderPersistenceFactory;
import com.team5.HAPark.Order.model.*;

public class FoodOrderFactory implements IOrderFactory {

    private final IFoodService foodService;

    public FoodOrderFactory(IFoodService foodService){
        this.foodService = foodService;
    }

    @Override
    public IOrderService createOrderService() {
        return new OrderService(createFoodOrderPersistence());
    }

    private IOrderPersistence createFoodOrderPersistence() {
        IOrderPersistenceFactory orderPersistenceFactory = new OrderPersistenceFactory();
        return orderPersistenceFactory.createFoodOrderPersistence(foodService);
    }
}