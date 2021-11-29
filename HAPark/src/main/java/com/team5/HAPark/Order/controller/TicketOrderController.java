package com.team5.HAPark.Order.controller;

import com.team5.HAPark.Cart.model.CartSummary;
import com.team5.HAPark.Order.DAO.IOrderPersistence;
import com.team5.HAPark.Order.TicketOrderFactory;
import com.team5.HAPark.Order.model.IOrder;
import com.team5.HAPark.Order.model.IOrderService;
import com.team5.HAPark.Order.model.TicketOrderItemAdapter;
import com.team5.HAPark.Ticket.model.TicketOrderItem;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TicketOrderController {

    @Autowired
    private CartSummary cart;

    @RequestMapping(value = "/saveticketorder", method = RequestMethod.GET)
    public void saveTicketOrder() {

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

    @GetMapping(value = "/orders/tickets")
    public String ticketOrderHistory(Model model) throws SQLException {

        MySQLDatabase dataBase = new MySQLDatabase();

        TicketOrderFactory orderFactory = new TicketOrderFactory();
        IOrderPersistence orderPersistence = orderFactory.createOrderPersistence(dataBase);
        IOrderService orderService = orderFactory.createOrderService(orderPersistence);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();

        List<IOrder> orders = orderService.getAllOrdersForUser(email);
        dataBase.close();

        model.addAttribute("orders",orders);

        return "OrderHistory";
    }

}
