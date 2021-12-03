package com.team5.HAPark.order.DAO;

public interface IOrderPersistenceFactory {

    IOrderPersistence createFoodOrderPersistence();

    IOrderPersistence createTicketOrderPersistence();
}
