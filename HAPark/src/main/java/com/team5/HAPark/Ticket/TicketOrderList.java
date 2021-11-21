package com.team5.HAPark.Ticket;

import java.util.List;

public class TicketOrderList {
    private List<TicketOrderItem> ticketOrderList;

    public TicketOrderList(List<TicketOrderItem> ticketOrderList) {
        this.ticketOrderList = ticketOrderList;
    }

    public List<TicketOrderItem> getTicketOrderList() {
        return ticketOrderList;
    }

    public void setTicketOrderList(List<TicketOrderItem> ticketOrderList) {
        this.ticketOrderList = ticketOrderList;
    }
}
