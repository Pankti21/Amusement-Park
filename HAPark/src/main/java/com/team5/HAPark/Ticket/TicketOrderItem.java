package com.team5.HAPark.Ticket;

public class TicketOrderItem {
    private Ticket ticket;
    private  int quantity;


    public TicketOrderItem(Ticket ticket, int quantity) {
        this.ticket = ticket;
        this.quantity = quantity;
    }


    public int getQuantity() {
        return this.quantity;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTicketType() {
        String ticketType = ticket.getTicketType();
        return ticketType;
    }

    public double getTicketPrice() {
        double ticketPrice = ticket.getTicketPrice();
        return ticketPrice;
    }
}
