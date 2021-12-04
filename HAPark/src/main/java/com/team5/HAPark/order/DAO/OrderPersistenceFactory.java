package com.team5.HAPark.order.DAO;

import com.team5.HAPark.database.mysql.MySQLDatabase;
import com.team5.HAPark.food.IFoodService;
import com.team5.HAPark.ticket.DAO.TicketPersistenceFactory;
import com.team5.HAPark.ticket.ITicketService;
import com.team5.HAPark.ticket.model.TicketService;

public class OrderPersistenceFactory implements IOrderPersistenceFactory{

    private static IOrderPersistence foodOrderPersistence;
    private static IOrderPersistence ticketOrderPersistence;

    @Override
    public IOrderPersistence createFoodOrderPersistence(IFoodService foodService) {
        if (foodOrderPersistence == null) {
            foodOrderPersistence = new MySQLFoodOrderPersistence(MySQLDatabase.getInstance(),foodService);
        }
        return foodOrderPersistence;
    }

    @Override
    public IOrderPersistence createTicketOrderPersistence(ITicketService ticketService) {
        if (ticketOrderPersistence == null) {
            ticketOrderPersistence = new MySQLTicketOrderPersistence(MySQLDatabase.getInstance(),new TicketService(new TicketPersistenceFactory().createTicketPersistence()));
        }
        return ticketOrderPersistence;
    }
}
