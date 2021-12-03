package com.team5.HAPark.order.DAO;

import com.team5.HAPark.database.mysql.MySQLDatabase;
import com.team5.HAPark.food.DAO.FoodPersistenceFactory;
import com.team5.HAPark.food.FoodService;
import com.team5.HAPark.ticket.DAO.TicketPersistenceFactory;
import com.team5.HAPark.ticket.TicketService;

public class OrderPersistenceFactory implements IOrderPersistenceFactory{

    private static IOrderPersistence foodOrderPersistence;
    private static IOrderPersistence ticketOrderPersistence;

    @Override
    public IOrderPersistence createFoodOrderPersistence() {
        if (foodOrderPersistence == null) {
            foodOrderPersistence = new MySQLFoodOrderPersistence(MySQLDatabase.getInstance(),new FoodService(new FoodPersistenceFactory().createFoodPersistence()));
        }
        return foodOrderPersistence;
    }

    @Override
    public IOrderPersistence createTicketOrderPersistence() {
        if (ticketOrderPersistence == null) {
            ticketOrderPersistence = new MySQLTicketOrderPersistence(MySQLDatabase.getInstance(),new TicketService(new TicketPersistenceFactory().createTicketPersistence()));
        }
        return ticketOrderPersistence;
    }
}
