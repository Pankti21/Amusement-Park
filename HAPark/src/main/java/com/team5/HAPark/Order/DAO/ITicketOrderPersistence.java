package com.team5.HAPark.Order.DAO;

import com.team5.HAPark.Order.TicketOrder;
import com.team5.HAPark.Ticket.TicketOrderItem;

import java.sql.SQLException;
import java.util.List;

public interface ITicketOrderPersistence {
    void saveOrder(TicketOrder order) throws SQLException;
    void saveOrderItem(int orderId, String ticketType, int quantity) throws SQLException;
    TicketOrder loadOrder(int orderId) throws SQLException;
    List<TicketOrderItem> loadOrderItems(int orderId) throws SQLException;
    List<TicketOrder> loadAllOrders(String email) throws SQLException;
}