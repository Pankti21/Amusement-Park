package com.team5.HAPark.Food;

import com.team5.HAPark.Order.model.IOrderItem;

public class FoodOrderItem implements IOrderItem {
    private Food food;
    private Integer quantity;

    public FoodOrderItem(Food food, int quantity) {
        this.food = food;
        this.quantity = quantity;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public double getPrice(){
        return food.getPrice();
    }

    public String getName(){
        return food.getName();
    }

    public String getId(){
        return food.getId();
    }
}
