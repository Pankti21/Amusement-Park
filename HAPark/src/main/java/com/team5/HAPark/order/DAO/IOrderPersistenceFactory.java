package com.team5.HAPark.order.DAO;

import com.team5.HAPark.food.IFoodService;
import com.team5.HAPark.ticket.ITicketService;

public interface IOrderPersistenceFactory {

    IOrderPersistence createFoodOrderPersistence(IFoodService foodService);

    IOrderPersistence createTicketOrderPersistence(ITicketService ticketService);
}
