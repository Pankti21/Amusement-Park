package com.team5.HAPark.Order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.team5.HAPark.Food.DAO.MySQLFoodPersistence;
import com.team5.HAPark.Food.FoodService;
import com.team5.HAPark.Order.DAO.MySQLFoodOrderPersistence;
import database.mysql.MySQLDatabase;
import org.springframework.web.bind.annotation.*;

@RestController
public class FoodOrderController {

    @RequestMapping(method = RequestMethod.POST,value = "/savefoodorder")
    public void save(@RequestBody Order order)
    {
        MySQLDatabase dataBase = new MySQLDatabase();
        MySQLFoodPersistence foodPersistence = new MySQLFoodPersistence(dataBase);
        FoodService foodService = new FoodService(foodPersistence);
        MySQLFoodOrderPersistence mySQLFoodOrderPersistence = new MySQLFoodOrderPersistence(dataBase,foodService);
        OrderService orderService = new OrderService(mySQLFoodOrderPersistence);

        orderService.saveOrder(order);

        dataBase.close();
    }

    @RequestMapping(value = "/foodorder/{orderId}", method = RequestMethod.GET)
    public Order displayOrder(@PathVariable int orderId) throws JsonProcessingException {

        MySQLDatabase dataBase = new MySQLDatabase();
        MySQLFoodPersistence foodPersistence = new MySQLFoodPersistence(dataBase);
        FoodService foodService = new FoodService(foodPersistence);
        MySQLFoodOrderPersistence mySQLFoodOrderPersistence = new MySQLFoodOrderPersistence(dataBase,foodService);
        OrderService orderService = new OrderService(mySQLFoodOrderPersistence);

        Order order = orderService.getOrder(orderId);
        dataBase.close();

        return order;
    }

}
