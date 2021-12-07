package com.team5.HAPark.cart.model;

import com.team5.HAPark.food.IFoodOrderItem;
import com.team5.HAPark.ticket.ITicketOrderItem;
import org.springframework.stereotype.Component;

@Component
public interface ICartSummary {
    void addTicketToCart(ITicketOrderItem ticket);
    void addFoodToCart(IFoodOrderItem food);
    void showCart();
    void removeTicketFromCart(ITicketOrderItem t);
    void removeFoodFromCart(IFoodOrderItem f);
    double getTicketAmount();
    double getFoodAmount();
    double getTotalAmount();
}
