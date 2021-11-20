package com.team5.HAPark.Order.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface IOrder {
    Integer getOrderId();

    void setOrderId(Integer orderId);

    String getMailId();

    void setMailId(String mailId);

    LocalDate getOrderDate();

    void setOrderDate(LocalDate orderDate);

    LocalTime getOrderTime();

    void setOrderTime(LocalTime orderTime);

    List<IOrderItem> getOrderItems();

    void setOrderItems(List<IOrderItem> orderItems);
}
