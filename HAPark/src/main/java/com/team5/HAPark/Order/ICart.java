package com.team5.HAPark.Order;

public interface ICart {
    double getTotalPrice();
    void addItem(IItem iItem, int quantity);
    void removeItem(IItem item, int quantity);
}
