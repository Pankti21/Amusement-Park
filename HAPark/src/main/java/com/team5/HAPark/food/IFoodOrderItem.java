package com.team5.HAPark.food;

import com.team5.HAPark.order.model.IOrderItem;

public interface IFoodOrderItem extends IOrderItem {
    Food getFood();

    void setQuantity(Integer quantity);

    void setPrice(Double price);

    void setName(String name);

    void setId(String id);
}
