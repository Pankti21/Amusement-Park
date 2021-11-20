package com.team5.HAPark.Cart;

import com.team5.HAPark.Food.DAO.MySQLFoodPersistence;
import com.team5.HAPark.Food.FoodService;
import com.team5.HAPark.Food.Menu;
import database.mysql.MySQLDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
public class SessionController {

    @Autowired private CartSummary cartSummary;

    @RequestMapping(value = "/testingCart")
    public String testing() throws SQLException {

        return cartSummary.toString();
    }

}
