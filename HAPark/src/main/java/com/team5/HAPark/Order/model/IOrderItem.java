package com.team5.HAPark.Order.model;

public interface IOrderItem {

    Integer getQuantity();

    double getPrice();

    double getTotalPrice();

    String getName();

    String getId();
}
