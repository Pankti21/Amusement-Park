package com.team5.HAPark.Food;

import com.team5.HAPark.Cart.CartSummary;
import com.team5.HAPark.Food.DAO.MySQLFoodPersistence;
import database.mysql.MySQLDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLException;

@Controller
public class MenuController {

    @Autowired private CartSummary cart;

    @GetMapping(value = "/menu")
    public String displayFoods(Model model) throws SQLException {

        MySQLDatabase dataBase = new MySQLDatabase();
        FoodService foodService = new FoodService(new MySQLFoodPersistence(dataBase));

        Menu menu = foodService.getMenu();
        dataBase.close();

        model.addAttribute("menu",menu.getFoodList());

        return "restaurant";
    }

    @PostMapping(value = "/menu/update")
    public RedirectView addFoodsToCart(@ModelAttribute("foodOrderList") FoodOrderList foodOrderList){
        for(FoodOrderItem foodOrderItem: foodOrderList.getFoodOrderList()){
            if (foodOrderItem.getQuantity()!=null && foodOrderItem.getQuantity()>0){
                cart.addFoodToCart(foodOrderItem);
            }
        }
        return new RedirectView("/menu");
    }
}

