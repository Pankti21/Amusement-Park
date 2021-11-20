package com.team5.HAPark.Order;

import com.team5.HAPark.Food.DAO.MySQLFoodPersistence;
import com.team5.HAPark.Food.FoodService;
import com.team5.HAPark.Order.DAO.IOrderPersistence;
import com.team5.HAPark.Order.DAO.MySQLFoodOrderPersistence;
import database.mysql.MySQLDatabase;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class FoodOrderController {

    @RequestMapping(method = RequestMethod.POST,value = "/savefoodorder")
    public void save(@RequestBody IOrder order)
    {
        MySQLDatabase dataBase = new MySQLDatabase();

        FoodOrderFactory orderFactory = new FoodOrderFactory();
        IOrderPersistence orderPersistence = orderFactory.createOrderPersistence(dataBase);
        IOrderService orderService = orderFactory.createOrderService(orderPersistence);

        orderService.saveOrder(order);

        dataBase.close();
    }

    @RequestMapping(value = "/foodorder/{orderId}", method = RequestMethod.GET)
    public IOrder getOrder(@PathVariable int orderId) {

        MySQLDatabase dataBase = new MySQLDatabase();

        FoodOrderFactory orderFactory = new FoodOrderFactory();
        IOrderPersistence orderPersistence = orderFactory.createOrderPersistence(dataBase);
        IOrderService orderService = orderFactory.createOrderService(orderPersistence);

        IOrder order = orderService.getOrder(orderId);
        dataBase.close();

        return order;
    }

    @RequestMapping(value = "/foodorder/",method = RequestMethod.GET)
    public List<IOrder> getOrdersForCurrentUser() throws SQLException {

        MySQLDatabase dataBase = new MySQLDatabase();

        FoodOrderFactory orderFactory = new FoodOrderFactory();
        IOrderPersistence orderPersistence = orderFactory.createOrderPersistence(dataBase);
        IOrderService orderService = orderFactory.createOrderService(orderPersistence);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();
        List<IOrder> orders = orderService.getAllOrdersForUser(email);
        dataBase.close();

        return orders;
    }
}
