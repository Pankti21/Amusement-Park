package com.team5.HAPark.Cart;


import com.team5.HAPark.Ticket.TicketOrderItem;
import com.team5.HAPark.Food.Food;

import java.util.ArrayList;
import java.util.ListIterator;

public class CartSummary {
    ArrayList<TicketOrderItem> ticket;
    ArrayList<Food> food;
    double ticketAmount;
    double foodAmount;
    double totalAmount;

    void CartItems() {
        this.ticket = new ArrayList<TicketOrderItem>();
        this.food = new ArrayList<Food>();
        double ticketAmount = 0;
        double foodAmount = 0;
        this.totalAmount = 0;

    }

    //Adding the items to cart
    public void addToCart(TicketOrderItem ticket, Food food) {
        this.ticket.add(ticket);
        this.food.add(food);
    }

    //Display the cart items
    public void showCart() {
        ListIterator<TicketOrderItem> ticketIterator = ticket.listIterator();
        ListIterator<Food> foodIterator = food.listIterator();
        while ((ticketIterator.hasNext() || (foodIterator.hasNext()))){
            TicketOrderItem ticketItem = ticketIterator.next();
            Food foodItem = foodIterator.next();
            System.out.println(ticketItem);
            System.out.println(foodItem);
        }
    }

    //Remove tickets from cart
    public void removeTicketFromCart(TicketOrderItem t) {
        ListIterator<TicketOrderItem> ticketIterator = ticket.listIterator();
        while (ticketIterator.hasNext()) {
            TicketOrderItem ticketItem = ticketIterator.next();
            if (ticketItem.getTicketType().equals(t.getTicketType())) {
                this.ticket.remove(t);
                break;
            }
        }
    }

    //Remove Food items from cart
    public void removeFoodFromCart(Food f) {
        ListIterator<Food> foodIterator = food.listIterator();
        while  (foodIterator.hasNext()){
            Food foodItem = foodIterator.next();
            if (foodItem.getId().equals(f.getId())) {
                this.food.remove(f);
                break;
            }
        }
    }

    //Get the tickets amount
    public double getTicketAmount() {
        ListIterator<TicketOrderItem> ticketIterator = ticket.listIterator();
        this.ticketAmount = 0;
        while (ticketIterator.hasNext()) {
            TicketOrderItem ticketItem = ticketIterator.next();
            this.ticketAmount = this.ticketAmount + (ticketItem.getQuantity() * ticketItem.getTicketPrice());
            }
        return this.ticketAmount;
        }

    //Get the Food items amount
    public double getFoodAmount() {
        ListIterator<Food> foodIterator = food.listIterator();
        this.foodAmount = 0;
        while (foodIterator.hasNext()) {
            Food foodItem = foodIterator.next();
            this.foodAmount = this.foodAmount + (foodItem.getPrice() * foodItem.getQuantity());
        }
        return this.foodAmount;
    }


    //Get the total amount to be paid
    public double getTotalAmount() {
        totalAmount = getTicketAmount() + getFoodAmount();
        return this.totalAmount;
    }



}


