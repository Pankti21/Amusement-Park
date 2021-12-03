package com.team5.HAPark.order;

import com.team5.HAPark.order.DAO.IOrderPersistence;
import com.team5.HAPark.order.DAO.IOrderPersistenceFactory;
import com.team5.HAPark.order.DAO.OrderPersistenceFactory;
import com.team5.HAPark.order.model.*;

public class TicketOrderFactory implements IOrderFactory {

    @Override
    public IOrderService createOrderService() {
        return new OrderService(createTicketOrderPersistence());
    }

    private IOrderPersistence createTicketOrderPersistence() {
        IOrderPersistenceFactory orderPersistenceFactory = new OrderPersistenceFactory();
        return orderPersistenceFactory.createTicketOrderPersistence();
    }
}
