package com.team5.HAPark.Order;

import com.team5.HAPark.Order.DAO.IOrderPersistence;
import com.team5.HAPark.Order.DAO.MySQLTicketOrderPersistence;
import com.team5.HAPark.Ticket.DAO.MySQLTicketPersistence;
import com.team5.HAPark.Ticket.TicketService;
import database.mysql.MySQLDatabase;

public class TicketOrderFactory implements IOrderFactory{
    
    @Override
    public IOrderService createOrderService(IOrderPersistence orderPersistence) {
        return new OrderService(orderPersistence);
    }

    @Override
    public IOrder createOrder() {
        return new Order();
    }

    @Override
    public IOrderItem createOrderItem() {
        return new OrderItem();
    }

    @Override
    public IOrderPersistence createOrderPersistence(MySQLDatabase database) {
        return new MySQLTicketOrderPersistence(database,new TicketService(new MySQLTicketPersistence(database)));
    }
}
