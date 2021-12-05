package com.team5.HAPark.cart.model;

import com.team5.HAPark.food.FoodOrderItem;
import com.team5.HAPark.ticket.ITicketOrderItem;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.ListIterator;

@Component
@SessionScope
public class CartSummary implements ICartSummary{
    private ArrayList<ITicketOrderItem> ticket;

    public ArrayList<ITicketOrderItem> getTicket() {
        return ticket;
    }

    public void setTicket(ArrayList<ITicketOrderItem> ticket) {
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
        this.ticket = new ArrayList<ITicketOrderItem>();
        this.food = new ArrayList<FoodOrderItem>();
        this.totalAmount = 0;
    }

    //Adding the tickets to cart
   @Override
    public void addTicketToCart(ITicketOrderItem ticket) {
        if (ticket.getQuantity()!=null && ticket.getQuantity()>0){
            this.ticket.add(ticket);
        }
    }

    //Adding the food items to cart
    @Override
    public void addFoodToCart(FoodOrderItem food) {
        if (food.getQuantity()!=null && food.getQuantity()>0){
            this.food.add(food);
        }
    }

    //Display the cart items
    @Override
    public void showCart() {
        ListIterator<ITicketOrderItem> ticketIterator = ticket.listIterator();
        ListIterator<FoodOrderItem> foodIterator = food.listIterator();
        while ((ticketIterator.hasNext() || (foodIterator.hasNext()))){
            ITicketOrderItem ticketItem = ticketIterator.next();
            FoodOrderItem foodItem = foodIterator.next();
            System.out.println(ticketItem);
            System.out.println(foodItem);
        }
    }

    //Remove tickets from cart
    @Override
    public void removeTicketFromCart(ITicketOrderItem t) {
        ListIterator<ITicketOrderItem> ticketIterator = ticket.listIterator();
        while (ticketIterator.hasNext()) {
            ITicketOrderItem ticketItem = ticketIterator.next();
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
    @Override
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
    @Override
    public double getTicketAmount() {
        ListIterator<ITicketOrderItem> ticketIterator = ticket.listIterator();
        this.ticketAmount = 0;
        while (ticketIterator.hasNext()) {
            ITicketOrderItem ticketItem = ticketIterator.next();
            this.ticketAmount = this.ticketAmount + (ticketItem.getQuantity() * ticketItem.getTicketPrice());
            }
        return this.ticketAmount;
        }

    //Get the Food items amount
    @Override
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
    @Override
    public double getTotalAmount() {
        totalAmount = getTicketAmount() + getFoodAmount();
        return this.totalAmount;
    }

    public void empty() {
        ticket.clear();
        food.clear();
    }
}


