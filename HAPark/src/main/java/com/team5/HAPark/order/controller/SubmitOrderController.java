package com.team5.HAPark.order.controller;

import com.team5.HAPark.cart.CartSummary;
import com.team5.HAPark.food.FoodOrderItem;
import com.team5.HAPark.order.DAO.IOrderPersistence;
import com.team5.HAPark.order.FoodOrderFactory;
import com.team5.HAPark.order.TicketOrderFactory;
import com.team5.HAPark.order.model.IOrder;
import com.team5.HAPark.order.model.IOrderService;
import com.team5.HAPark.order.model.TicketOrderItemAdapter;
import com.team5.HAPark.ticket.TicketOrderItem;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

@Controller
public class SubmitOrderController {

    @Autowired
    private CartSummary cart;

    @GetMapping(value = "/submitorder")
    public RedirectView submitOrder(){
        saveTicketOrder();
        saveFoodOrder();
        cart.empty();
        return new RedirectView("/orders");
    }

    public void saveTicketOrder() {

        ArrayList<TicketOrderItem> ticketOrderItems = cart.getTicket();
        MySQLDatabase dataBase = MySQLDatabase.getInstance();

        TicketOrderFactory orderFactory = new TicketOrderFactory();
        IOrderService orderService = orderFactory.createOrderService();

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

    public void saveFoodOrder() {

        ArrayList<FoodOrderItem> foodOrderItems = cart.getFood();
        MySQLDatabase dataBase = MySQLDatabase.getInstance();

        FoodOrderFactory orderFactory = new FoodOrderFactory();
        IOrderService orderService = orderFactory.createOrderService();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();

        IOrder order = orderService.createOrderFromItemQuantities(email,foodOrderItems);
        orderService.saveOrder(order);

        dataBase.close();
    }
}
