package com.team5.HAPark.order;

import com.team5.HAPark.order.DAO.IOrderPersistence;
import com.team5.HAPark.order.DAO.IOrderPersistenceFactory;
import com.team5.HAPark.order.DAO.OrderPersistenceFactory;
import com.team5.HAPark.order.model.*;
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
