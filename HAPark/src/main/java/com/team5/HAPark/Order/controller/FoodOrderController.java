package com.team5.HAPark.Order.controller;

import com.team5.HAPark.Cart.CartSummary;
import com.team5.HAPark.Food.FoodOrderItem;
import com.team5.HAPark.Order.DAO.IOrderPersistence;
import com.team5.HAPark.Order.FoodOrderFactory;
import com.team5.HAPark.Order.model.IOrder;
import com.team5.HAPark.Order.model.IOrderService;
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
public class FoodOrderController {

    @Autowired
    private CartSummary cart;

    @RequestMapping(value = "/savefoodorder", method = RequestMethod.GET)
    public void saveFoodOrder() {

        ArrayList<FoodOrderItem> foodOrderItems = cart.getFood();
        MySQLDatabase dataBase = new MySQLDatabase();

        FoodOrderFactory orderFactory = new FoodOrderFactory();
        IOrderPersistence orderPersistence = orderFactory.createOrderPersistence(dataBase);
        IOrderService orderService = orderFactory.createOrderService(orderPersistence);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();

        IOrder order = orderService.createOrderFromItemQuantities(email,foodOrderItems);
        orderService.saveOrder(order);

        dataBase.close();
    }

    @GetMapping(value = "/orders/food")
    public String foodOrderHistory(Model model) throws SQLException {

        MySQLDatabase dataBase = new MySQLDatabase();

        FoodOrderFactory orderFactory = new FoodOrderFactory();
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
