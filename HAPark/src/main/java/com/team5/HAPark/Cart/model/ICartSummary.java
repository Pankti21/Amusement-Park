package com.team5.HAPark.Cart.model;

import com.team5.HAPark.Food.FoodOrderItem;
import com.team5.HAPark.Ticket.model.TicketOrderItem;

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
