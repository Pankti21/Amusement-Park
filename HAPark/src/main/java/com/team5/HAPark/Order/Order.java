package com.team5.HAPark.Order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Order {
    private Integer orderId;
    private String mailId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private Map<IItem,Integer> orderItemQuantities;

    public Order() {}

    public Order(Integer orderId, String mailId, LocalDate orderDate, LocalTime orderTime, Map<IItem, Integer> orderItemQuantities) {
        this.orderId = orderId;
        this.mailId = mailId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderItemQuantities = orderItemQuantities;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalTime orderTime) {
        this.orderTime = orderTime;
    }

    public Map<IItem, Integer> getOrderItemQuantities() {
        return orderItemQuantities;
    }

    public void setOrderItemQuantities(Map<IItem, Integer> orderItemQuantities) {
        this.orderItemQuantities = orderItemQuantities;
    }
}
