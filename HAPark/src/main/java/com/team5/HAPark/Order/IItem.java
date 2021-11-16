package com.team5.HAPark.Order;

public interface IItem {
    String getId();
    String getName();
    Double getPrice();
    void setId(String id);
    void setName(String name);
    void setPrice(double price);
}
