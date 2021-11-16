package com.team5.HAPark.Order;

import java.time.LocalDate;
import java.time.LocalTime;

public interface IOrder {
    String getOrderId();
    String getMailId();
    LocalTime getOrderTime();
    LocalDate getOrderDate();
    void setMailId(String  mailId);
    void setOrderTime(LocalTime time);
    void setOrderDate(LocalDate date);
    void addItem(IItem item, int quantity);
}
