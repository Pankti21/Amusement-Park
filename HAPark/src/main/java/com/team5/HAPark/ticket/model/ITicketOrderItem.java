package com.team5.HAPark.ticket.model;

public interface ITicketOrderItem {
    public Integer getQuantity();

    public Ticket getTicket();

    public void setTicket(Ticket ticket);

    public void setQuantity(Integer quantity);

    public String getTicketType() ;

    public void setTicketType(String ticketType) ;

    public double getTicketPrice() ;

    public double getTotalPrice() ;
}
