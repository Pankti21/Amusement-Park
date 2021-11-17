package com.team5.HAPark.Food;

public class FoodOrderItem {
    Food food;
    int quantity;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
