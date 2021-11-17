package com.team5.HAPark.Order;

import com.team5.HAPark.Ticket.TicketOrderItem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TicketOrder {

    private Integer orderId;
    private String mailId;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private List<TicketOrderItem> orderItems;

    public TicketOrder() {}

    public TicketOrder(Integer orderId, String mailId, LocalDate orderDate, LocalTime orderTime, List<TicketOrderItem> orderItems) {
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

    public List<TicketOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<TicketOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
