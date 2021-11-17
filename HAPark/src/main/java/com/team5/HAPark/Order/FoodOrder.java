package com.team5.HAPark.Order;

import com.team5.HAPark.Food.FoodOrderItem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class FoodOrder {
    private Integer orderId;
    private String mailId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private List<FoodOrderItem> orderItems;

    public FoodOrder() {}

    public FoodOrder(Integer orderId, String mailId, LocalDate orderDate, LocalTime orderTime, List<FoodOrderItem> orderItems) {
        this.orderId = orderId;
        this.mailId = mailId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.orderItems = orderItems;
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

    public List<FoodOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<FoodOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
