package com.team5.HAPark.Order.DAO;

import com.team5.HAPark.Food.IFoodService;
import com.team5.HAPark.ticket.ITicketService;

public interface IOrderPersistenceFactory {

    IOrderPersistence createFoodOrderPersistence(IFoodService foodService);

    IOrderPersistence createTicketOrderPersistence(ITicketService ticketService);
}
