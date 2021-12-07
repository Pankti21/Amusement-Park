package com.team5.HAPark.food;

import java.util.List;

public class FoodOrderList {

    private List<FoodOrderItem> foodOrderList;

    public FoodOrderList(List<FoodOrderItem> foodOrderList) {
        this.foodOrderList = foodOrderList;
    }

    public List<FoodOrderItem> getFoodOrderList() {
        return foodOrderList;
    }

    public void setFoodOrderList(List<FoodOrderItem> foodOrderList) {
        this.foodOrderList = foodOrderList;
    }
}
