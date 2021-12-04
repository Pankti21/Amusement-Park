package com.team5.HAPark.order;

import com.team5.HAPark.food.IFoodService;
import com.team5.HAPark.order.DAO.IOrderPersistence;
import com.team5.HAPark.order.DAO.IOrderPersistenceFactory;
import com.team5.HAPark.order.DAO.OrderPersistenceFactory;
import com.team5.HAPark.order.model.*;

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