package com.team5.HAPark.cart;

import com.team5.HAPark.food.FoodOrderItem;
import com.team5.HAPark.ticket.TicketOrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartSummaryTest {
    private FoodOrderItem foodOrderItem;
    private TicketOrderItem ticketOrderItem;
    private CartSummary cartSummary;

    @BeforeEach
    void init() {
        foodOrderItem = new FoodOrderItem();
        ticketOrderItem = new TicketOrderItem();
        cartSummary = new CartSummary();
    }

    @Test
    void addTicketToCart() {
        cartSummary.addTicketToCart(ticketOrderItem);
        ArrayList<TicketOrderItem> ticketOrderItemList = cartSummary.getTicket();
        assertTrue(ticketOrderItemList.contains(ticketOrderItem));
    }

    @Test
    void addFoodToCart() {
        cartSummary.addFoodToCart(foodOrderItem);
        List<FoodOrderItem> foodOrderItemsList = cartSummary.getFood();
        assertTrue(foodOrderItemsList.contains(foodOrderItem));
    }

    @Test
    void removeTicketFromCart() {
        cartSummary.removeTicketFromCart(ticketOrderItem);
        ArrayList<TicketOrderItem> ticketOrderItemList = cartSummary.getTicket();
        assertFalse(ticketOrderItemList.contains(ticketOrderItem));
    }

    @Test
    void removeFoodFromCart() {
        cartSummary.removeFoodFromCart(foodOrderItem);
        List<FoodOrderItem> foodOrderItemsList = cartSummary.getFood();
        assertFalse(foodOrderItemsList.contains(foodOrderItem));
    }

    @Test
    void getTicketAmount() {
        cartSummary.getTicketAmount();
        ArrayList<TicketOrderItem> ticketOrderItemList = cartSummary.getTicket();
        assertFalse(ticketOrderItemList.contains(ticketOrderItem));
    }

    @Test
    void getFoodAmount() {
        cartSummary.getFoodAmount();
        List<FoodOrderItem> foodOrderItemsList = cartSummary.getFood();
        assertFalse(foodOrderItemsList.contains(foodOrderItem));
    }

    @Test
    void getTotalAmount() {
        double cartAmount = cartSummary.getTotalAmount();
        double ticketAmount = cartSummary.getTicketAmount();
        double foodAmount = cartSummary.getFoodAmount();
        assertEquals(cartAmount, (ticketAmount+foodAmount));
    }

    @Test
    void empty() {
        cartSummary.addFoodToCart(foodOrderItem);
        cartSummary.addTicketToCart(ticketOrderItem);
        cartSummary.empty();
        assertTrue(cartSummary.getFood().isEmpty()&&cartSummary.getTicket().isEmpty());
    }
}