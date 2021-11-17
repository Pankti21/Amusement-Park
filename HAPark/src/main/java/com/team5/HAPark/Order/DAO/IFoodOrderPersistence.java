package com.team5.HAPark.Order.DAO;

import com.team5.HAPark.Food.FoodOrderItem;
import com.team5.HAPark.Order.FoodOrder;

import java.sql.SQLException;
import java.util.List;

public interface IFoodOrderPersistence {
    void saveOrder(FoodOrder order) throws SQLException;
    void saveOrderItem(int orderId, String itemId, int quantity) throws SQLException;
    FoodOrder loadOrder(int orderId) throws SQLException;
    List<FoodOrderItem> loadOrderItems(int orderId) throws SQLException;
    List<FoodOrder> loadAllOrders(String email) throws SQLException;
}