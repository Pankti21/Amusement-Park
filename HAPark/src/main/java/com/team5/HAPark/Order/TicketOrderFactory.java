package com.team5.HAPark.Order;

import com.team5.HAPark.Order.DAO.IOrderPersistence;
import com.team5.HAPark.Order.DAO.IOrderPersistenceFactory;
import com.team5.HAPark.Order.DAO.OrderPersistenceFactory;
import com.team5.HAPark.Order.model.*;
import com.team5.HAPark.ticket.ITicketService;

public class TicketOrderFactory implements IOrderFactory {

    private final ITicketService ticketService;

    public TicketOrderFactory(ITicketService ticketService){
        this.ticketService = ticketService;
    }

    @Override
    public IOrderService createOrderService() {
        return new OrderService(createTicketOrderPersistence());
    }

    private IOrderPersistence createTicketOrderPersistence() {
        IOrderPersistenceFactory orderPersistenceFactory = new OrderPersistenceFactory();
        return orderPersistenceFactory.createTicketOrderPersistence(ticketService);
    }
}
