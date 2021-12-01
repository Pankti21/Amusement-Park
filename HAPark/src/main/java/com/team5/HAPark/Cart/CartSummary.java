package com.team5.HAPark.Cart;

import com.team5.HAPark.Food.FoodOrderItem;
import com.team5.HAPark.Ticket.TicketOrderItem;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.ListIterator;

@Component
@SessionScope
public class CartSummary {
    private ArrayList<TicketOrderItem> ticket;

    public ArrayList<TicketOrderItem> getTicket() {
        return ticket;
    }

    public void setTicket(ArrayList<TicketOrderItem> ticket) {
        this.ticket = ticket;
    }

    public ArrayList<FoodOrderItem> getFood() {
        return food;
    }

    public void setFood(ArrayList<FoodOrderItem> food) {
        this.food = food;
    }

    public void setTicketAmount(double ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public void setFoodAmount(double foodAmount) {
        this.foodAmount = foodAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    private ArrayList<FoodOrderItem> food;
    double ticketAmount;
    double foodAmount;
    double totalAmount;

    public CartSummary() {
        this.ticket = new ArrayList<TicketOrderItem>();
        this.food = new ArrayList<FoodOrderItem>();
        this.totalAmount = 0;
    }

    //Adding the tickets to cart
    public void addTicketToCart(TicketOrderItem ticket) {
        this.ticket.add(ticket);
    }

    //Adding the food items to cart
    public void addFoodToCart(FoodOrderItem food) {
        this.food.add(food);
    }

    //Display the cart items
    public void showCart() {
        ListIterator<TicketOrderItem> ticketIterator = ticket.listIterator();
        ListIterator<FoodOrderItem> foodIterator = food.listIterator();
        while ((ticketIterator.hasNext() || (foodIterator.hasNext()))){
            TicketOrderItem ticketItem = ticketIterator.next();
            FoodOrderItem foodItem = foodIterator.next();
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
                ticketItem.setQuantity(ticketItem.getQuantity()-t.getQuantity());
                if((ticketItem.getQuantity() <= 0 )) {
                    ticket.remove(ticketItem);
                }
                break;
            }
        }
    }

    //Remove Food items from cart
    public void removeFoodFromCart(FoodOrderItem f) {
        ListIterator<FoodOrderItem> foodIterator = food.listIterator();
        while  (foodIterator.hasNext()){
            FoodOrderItem foodItem = foodIterator.next();
            if (foodItem.getId().equals(f.getId())) {
                foodItem.setQuantity(foodItem.getQuantity()-f.getQuantity());
                if((foodItem.getQuantity() <= 0 )) {
                    food.remove(foodItem);
                }
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
        ListIterator<FoodOrderItem> foodIterator = food.listIterator();
        this.foodAmount = 0;
        while (foodIterator.hasNext()) {
            FoodOrderItem foodItem = foodIterator.next();
            this.foodAmount = this.foodAmount + (foodItem.getPrice() * foodItem.getQuantity());
        }
        return this.foodAmount;
    }


    //Get the total amount to be paid
    public double getTotalAmount() {
        totalAmount = getTicketAmount() + getFoodAmount();
        return this.totalAmount;
    }

    public void empty() {
        ticket.clear();
        food.clear();
    }
}


