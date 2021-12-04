package com.team5.HAPark.Food;

import com.team5.HAPark.Food.DAO.FoodPersistenceFactory;
import com.team5.HAPark.Database.mysql.MySQLDatabase;
import com.team5.HAPark.Cart.model.CartSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLException;

@Controller
public class MenuController {

    @Autowired private CartSummary cart;

    @GetMapping(value = "/menu")
    public String displayFoods(Model model) throws SQLException {

        MySQLDatabase dataBase = MySQLDatabase.getInstance();
        FoodService foodService = new FoodService(new FoodPersistenceFactory().createFoodPersistence());

        Menu menu = foodService.getMenu();
        dataBase.close();

        model.addAttribute("menu", menu.getFoodList());

        return "Menu";
    }

    @PostMapping(value = "/menu/update")
    public RedirectView addFoodsToCart(@ModelAttribute("foodOrderList") FoodOrderList foodOrderList, RedirectAttributes redirectAttributes){

        for(FoodOrderItem foodOrderItem: foodOrderList.getFoodOrderList()){

            if (foodOrderItem.getQuantity()!=null && foodOrderItem.getQuantity()>0){
                cart.addFoodToCart(foodOrderItem);
            }
        }
        redirectAttributes.addFlashAttribute("message", "Cart updated");
        return new RedirectView("/menu");
    }
}

