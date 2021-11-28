package com.team5.HAPark.Food;

import com.team5.HAPark.Order.model.IOrderItem;

public class FoodOrderItem implements IOrderItem {
    private Food food;
    private Integer quantity;

    public FoodOrderItem() {
        food = new Food();
    }




    public Food getFood() {
        return food;
    }


    public FoodOrderItem(Food food, Integer quantity) {
        this.food = food;
        this.quantity = quantity;
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

    @Override
    public double getTotalPrice() {
        return getPrice() * getQuantity();
    }

    public void setPrice(Double price){
        food.setPrice(price);
    }

    public String getName(){
        return food.getName();
    }

    public void setName(String name){
        food.setName(name);
    }

    public String getId(){
        return food.getId();
    }

    public void setId(String id){
        food.setId(id);
    }
}
