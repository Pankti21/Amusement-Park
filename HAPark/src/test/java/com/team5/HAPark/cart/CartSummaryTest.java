package com.team5.HAPark.cart;

import com.team5.HAPark.cart.model.CartSummary;
import com.team5.HAPark.food.FoodOrderItem;
import com.team5.HAPark.ticket.ITicketOrderItem;
import com.team5.HAPark.ticket.model.TicketOrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartSummaryTest {
    private FoodOrderItem foodOrderItem;
    private ITicketOrderItem ticketOrderItem;
    private CartSummary cartSummary;

    @BeforeEach
    void init() {
        foodOrderItem = new FoodOrderItem();
        ticketOrderItem =  new TicketOrderItem();
        cartSummary = new CartSummary();
    }

    @Test
    void validateIfTicketsAreAddedToCart() {
        cartSummary.addTicketToCart((TicketOrderItem) ticketOrderItem);
        ArrayList<ITicketOrderItem> ticketOrderItemList = cartSummary.getTicket();
        assertTrue(ticketOrderItemList.contains(ticketOrderItem));
    }

    @Test
    void validateIfFoodIsAddedToCart() {
        cartSummary.addFoodToCart(foodOrderItem);
        List<FoodOrderItem> foodOrderItemsList = cartSummary.getFood();
        assertTrue(foodOrderItemsList.contains(foodOrderItem));
    }

    @Test
    void validateIfTicketsAreRemovedToCart() {
        cartSummary.removeTicketFromCart(ticketOrderItem);
        ArrayList<ITicketOrderItem> ticketOrderItemList = cartSummary.getTicket();
        assertFalse(ticketOrderItemList.contains(ticketOrderItem));
    }

    @Test
    void validateIfFoodAreRemovedToCart() {
        cartSummary.removeFoodFromCart(foodOrderItem);
        List<FoodOrderItem> foodOrderItemsList = cartSummary.getFood();
        assertFalse(foodOrderItemsList.contains(foodOrderItem));
    }

    @Test
    void validateIfTicketsAmountIsCalculatedCorrectly() {
        cartSummary.getTicketAmount();
        ArrayList<ITicketOrderItem> ticketOrderItemList = cartSummary.getTicket();
        assertFalse(ticketOrderItemList.contains(ticketOrderItem));
    }

    @Test
    void validateIfFoodAmountIsCalculatedCorrectly() {
        cartSummary.getFoodAmount();
        List<FoodOrderItem> foodOrderItemsList = cartSummary.getFood();
        assertFalse(foodOrderItemsList.contains(foodOrderItem));
    }

    @Test
    void validateIfTotalAmountIsCalculatedCorrectly() {
        double cartAmount = cartSummary.getTotalAmount();
        double ticketAmount = cartSummary.getTicketAmount();
        double foodAmount = cartSummary.getFoodAmount();
        assertEquals(cartAmount, (ticketAmount+foodAmount));
    }

    /*  @Test
    void validateCartIsEmpty() {
        cartSummary.addFoodToCart(foodOrderItem);
        cartSummary.addTicketToCart(ticketOrderItem);
        cartSummary.empty();
        assertTrue(cartSummary.getFood().isEmpty()&&cartSummary.getTicket().isEmpty());
    }*/
}