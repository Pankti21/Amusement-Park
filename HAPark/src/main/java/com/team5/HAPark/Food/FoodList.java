package com.team5.HAPark.Food;

import java.util.List;

public class FoodList {
    private List<FoodQuantity> foodList;

    public FoodList(List<FoodQuantity> foodList) {
        this.foodList = foodList;
    }

    public List<FoodQuantity> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<FoodQuantity> foodList) {
        this.foodList = foodList;
    }
}
