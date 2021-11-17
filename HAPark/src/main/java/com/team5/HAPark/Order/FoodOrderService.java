package com.team5.HAPark.Order;

import com.team5.HAPark.Food.FoodOrderItem;
import com.team5.HAPark.Order.DAO.IFoodOrderPersistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class FoodOrderService {

    private IFoodOrderPersistence orderPersistence;

    public FoodOrderService(IFoodOrderPersistence orderPersistence){
        this.orderPersistence = orderPersistence;
    }

    public FoodOrder createOrderFromItemQuantities(String userId, List<FoodOrderItem> foodOrderItems){

        FoodOrder order = null;

        if (foodOrderItems != null && !foodOrderItems.isEmpty()){
            order = new FoodOrder();
            order.setOrderDate(LocalDate.now());
            order.setOrderTime(LocalTime.now());
            order.setMailId(userId);
            order.setOrderItems(foodOrderItems);
        }

        return order;
    }

    public void saveOrder(FoodOrder order){
        try {
            orderPersistence.saveOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public FoodOrder getOrder(int orderId){

        FoodOrder order = null;

        try {
            order = orderPersistence.loadOrder(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public List<FoodOrder> getAllOrdersForUser(String email) throws SQLException {

        List<FoodOrder> orders = orderPersistence.loadAllOrders(email);

        if (orders == null){
            orders = new ArrayList<>();
        }

        return orders;
    }
}