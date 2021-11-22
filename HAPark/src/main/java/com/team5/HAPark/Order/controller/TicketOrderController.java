package com.team5.HAPark.Order.controller;

import com.team5.HAPark.Cart.CartSummary;
import com.team5.HAPark.Order.DAO.IOrderPersistence;
import com.team5.HAPark.Order.TicketOrderFactory;
import com.team5.HAPark.Order.model.IOrder;
import com.team5.HAPark.Order.model.IOrderService;
import com.team5.HAPark.Order.model.TicketOrderItemAdapter;
import com.team5.HAPark.Ticket.TicketOrderItem;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TicketOrderController {

    @Autowired
    private CartSummary cart;

    @RequestMapping(method = RequestMethod.GET,value = "/saveticketorder")
    public void save() {

        ArrayList<TicketOrderItem> ticketOrderItems = cart.getTicket();
        MySQLDatabase dataBase = new MySQLDatabase();

        TicketOrderFactory orderFactory = new TicketOrderFactory();
        IOrderPersistence orderPersistence = orderFactory.createOrderPersistence(dataBase);
        IOrderService orderService = orderFactory.createOrderService(orderPersistence);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();

        ArrayList<TicketOrderItemAdapter> ticketOrderItemAdapters = new ArrayList<>();

        for (TicketOrderItem ticketOrderItem:ticketOrderItems){
            ticketOrderItemAdapters.add(new TicketOrderItemAdapter(ticketOrderItem));
        }

        IOrder order = orderService.createOrderFromItemQuantities(email,ticketOrderItemAdapters);
        orderService.saveOrder(order);

        dataBase.close();
    }

    @RequestMapping(value = "/ticketorder/{orderId}", method = RequestMethod.GET)
    public IOrder getOrder(@PathVariable int orderId) {

        MySQLDatabase dataBase = new MySQLDatabase();

        TicketOrderFactory orderFactory = new TicketOrderFactory();
        IOrderPersistence orderPersistence = orderFactory.createOrderPersistence(dataBase);
        IOrderService orderService = orderFactory.createOrderService(orderPersistence);

        IOrder order = orderService.getOrder(orderId);
        dataBase.close();

        return order;
    }

    @RequestMapping(value = "/ticketorder/",method = RequestMethod.GET)
    public List<IOrder> getOrdersForCurrentUser() throws SQLException {

        MySQLDatabase dataBase = new MySQLDatabase();

        TicketOrderFactory orderFactory = new TicketOrderFactory();
        IOrderPersistence orderPersistence = orderFactory.createOrderPersistence(dataBase);
        IOrderService orderService = orderFactory.createOrderService(orderPersistence);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();
        List<IOrder> orders = orderService.getAllOrdersForUser(email);
        dataBase.close();

        return orders;
    }
}
