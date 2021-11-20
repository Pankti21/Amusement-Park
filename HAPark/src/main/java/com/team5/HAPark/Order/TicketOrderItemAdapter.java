package com.team5.HAPark.Order;

import com.team5.HAPark.Ticket.TicketOrderItem;

public class TicketOrderItemAdapter extends OrderItem {
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
    public String getName() {
        return ticketOrderItem.getTicketType();
    }
}
