package com.team5.HAPark.Order.controller;

import com.team5.HAPark.Cart.CartSummary;
import com.team5.HAPark.Food.DAO.FoodPersistenceFactory;
import com.team5.HAPark.Food.DAO.IFoodPersistenceFactory;
import com.team5.HAPark.Food.FoodOrderItem;
import com.team5.HAPark.Food.FoodService;
import com.team5.HAPark.Food.IFoodService;
import com.team5.HAPark.Order.FoodOrderFactory;
import com.team5.HAPark.Order.TicketOrderFactory;
import com.team5.HAPark.Order.model.IOrder;
import com.team5.HAPark.Order.model.IOrderService;
import com.team5.HAPark.Order.model.TicketOrderItemAdapter;
import com.team5.HAPark.Ticket.DAO.ITicketPersistenceFactory;
import com.team5.HAPark.Ticket.DAO.TicketPersistenceFactory;
import com.team5.HAPark.Ticket.ITicketService;
import com.team5.HAPark.Ticket.TicketOrderItem;
import com.team5.HAPark.Database.mysql.MySQLDatabase;
import com.team5.HAPark.Ticket.TicketService;
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

        ITicketPersistenceFactory ticketPersistenceFactory = new TicketPersistenceFactory();
        ITicketService ticketService = new TicketService(ticketPersistenceFactory.createTicketPersistence());
        TicketOrderFactory orderFactory = new TicketOrderFactory(ticketService);
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

        IFoodPersistenceFactory foodPersistenceFactory = new FoodPersistenceFactory();
        IFoodService foodService = new FoodService(foodPersistenceFactory.createFoodPersistence());
        FoodOrderFactory orderFactory = new FoodOrderFactory(foodService);
        IOrderService orderService = orderFactory.createOrderService();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();

        IOrder order = orderService.createOrderFromItemQuantities(email,foodOrderItems);
        orderService.saveOrder(order);

        dataBase.close();
    }
}
