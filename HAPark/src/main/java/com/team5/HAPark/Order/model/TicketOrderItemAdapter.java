package com.team5.HAPark.Order.model;

import com.team5.HAPark.Ticket.TicketOrderItem;

public class TicketOrderItemAdapter implements IOrderItem {

    private TicketOrderItem ticketOrderItem;

    public TicketOrderItemAdapter(TicketOrderItem ticketOrderItem) {
        this.ticketOrderItem = ticketOrderItem;
    }

    public TicketOrderItem getTicketOrderItem() {
        return ticketOrderItem;
    }

    @Override
    public String getId() {
        return ticketOrderItem.getTicketType();
    }

    @Override
    public Integer getQuantity() {
        return ticketOrderItem.getQuantity();
    }

    @Override
    public double getPrice() {
        return ticketOrderItem.getTicketPrice();
    }

    @Override
    public double getTotalPrice() {
        return getPrice() * getQuantity();
    }

    @Override
    public String getName() {
        return ticketOrderItem.getTicketType();
    }
}
