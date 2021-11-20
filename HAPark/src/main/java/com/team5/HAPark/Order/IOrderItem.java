package com.team5.HAPark.Order;

public interface IOrderItem {
    int getQuantity();

    void setQuantity(int quantity);

    double getPrice();

    String getName();

    String getId();
}
