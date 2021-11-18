package com.team5.HAPark.Order;

import com.team5.HAPark.Food.DAO.MySQLFoodPersistence;
import com.team5.HAPark.Food.FoodService;
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
    public void save(@RequestBody FoodOrder order)
    {
        MySQLDatabase dataBase = new MySQLDatabase();
        MySQLFoodPersistence foodPersistence = new MySQLFoodPersistence(dataBase);
        FoodService foodService = new FoodService(foodPersistence);
        MySQLFoodOrderPersistence mySQLFoodOrderPersistence = new MySQLFoodOrderPersistence(dataBase,foodService);
        FoodOrderService foodOrderService = new FoodOrderService(mySQLFoodOrderPersistence);

        foodOrderService.saveOrder(order);

        dataBase.close();
    }

    @RequestMapping(value = "/foodorder/{orderId}", method = RequestMethod.GET)
    public FoodOrder getOrder(@PathVariable int orderId) {

        MySQLDatabase dataBase = new MySQLDatabase();
        MySQLFoodPersistence foodPersistence = new MySQLFoodPersistence(dataBase);
        FoodService foodService = new FoodService(foodPersistence);
        MySQLFoodOrderPersistence mySQLFoodOrderPersistence = new MySQLFoodOrderPersistence(dataBase,foodService);
        FoodOrderService foodOrderService = new FoodOrderService(mySQLFoodOrderPersistence);

        FoodOrder order = foodOrderService.getOrder(orderId);
        dataBase.close();

        return order;
    }

    @RequestMapping(value = "/foodorder/",method = RequestMethod.GET)
    public List<FoodOrder> getOrdersForCurrentUser() throws SQLException {

        MySQLDatabase dataBase = new MySQLDatabase();
        MySQLFoodPersistence foodPersistence = new MySQLFoodPersistence(dataBase);
        FoodService foodService = new FoodService(foodPersistence);
        MySQLFoodOrderPersistence mySQLFoodOrderPersistence = new MySQLFoodOrderPersistence(dataBase,foodService);
        FoodOrderService foodOrderService = new FoodOrderService(mySQLFoodOrderPersistence);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();
        List<FoodOrder> orders = foodOrderService.getAllOrdersForUser(email);
        dataBase.close();

        return orders;
    }
}
