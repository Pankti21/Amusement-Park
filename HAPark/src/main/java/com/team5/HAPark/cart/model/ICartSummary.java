package com.team5.HAPark.cart.model;

import com.team5.HAPark.food.FoodOrderItem;
import com.team5.HAPark.ticket.model.TicketOrderItem;

public interface ICartSummary {
    void addTicketToCart(TicketOrderItem ticket);
    void addFoodToCart(FoodOrderItem food);
    void showCart();
    void removeTicketFromCart(TicketOrderItem t);
    void removeFoodFromCart(FoodOrderItem f);
    double getTicketAmount();
    double getFoodAmount();
    double getTotalAmount();
}
