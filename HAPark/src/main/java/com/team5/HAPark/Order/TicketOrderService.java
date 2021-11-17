package com.team5.HAPark.Order;

import com.team5.HAPark.Order.DAO.ITicketOrderPersistence;
import com.team5.HAPark.Ticket.TicketOrderItem;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TicketOrderService {

    private ITicketOrderPersistence orderPersistence;

    public TicketOrderService(ITicketOrderPersistence orderPersistence){
        this.orderPersistence = orderPersistence;
    }

    public TicketOrder createOrderFromItemQuantities(String userId, List<TicketOrderItem> OrderItems){

        TicketOrder order = null;

        if (OrderItems != null && !OrderItems.isEmpty()){
            order = new TicketOrder();
            order.setOrderDate(LocalDate.now());
            order.setOrderTime(LocalTime.now());
            order.setMailId(userId);
            order.setOrderItems(OrderItems);
        }

        return order;
    }

    public void saveOrder(TicketOrder order){
        try {
            orderPersistence.saveOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public TicketOrder getOrder(int orderId){

        TicketOrder order = null;

        try {
            order = orderPersistence.loadOrder(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return order;
    }

    public List<TicketOrder> getAllOrdersForUser(String email) throws SQLException {

        List<TicketOrder> orders = orderPersistence.loadAllOrders(email);

        if (orders == null){
            orders = new ArrayList<>();
        }

        return orders;
    }
}
