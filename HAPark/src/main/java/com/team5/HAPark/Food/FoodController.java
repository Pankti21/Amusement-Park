package com.team5.HAPark.Food;

import com.team5.HAPark.Food.DAO.MySQLFoodPersistence;
import database.mysql.MySQLDatabase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FoodController {

    @GetMapping(value = "/menu")
    public String getAllFoods(Model model) throws SQLException {

        MySQLDatabase dataBase = new MySQLDatabase();
        FoodService foodService = new FoodService(new MySQLFoodPersistence(dataBase));

        Menu menu = foodService.getMenu();
        dataBase.close();

        model.addAttribute("menu",menu.getFoodList());

        return "restaurant";
    }

    @PostMapping(value = "/menu/update")
    public RedirectView getAllFoods(@RequestParam("quantity") Integer[] quantities){
        System.out.println(quantities);
        //get info from request
        //todo;
        //get cart, add to cart

        //need info from TA
        //move to cart controller??
        return new RedirectView("/menu");
    }

    /*
    @RequestMapping(value = "/menu/{id}")
    public Food getFood(@PathVariable String id) throws SQLException {

        MySQLDatabase dataBase = new MySQLDatabase();
        FoodService foodService = new FoodService(new MySQLFoodPersistence(dataBase));

        Food food = foodService.getFood(id);
        dataBase.close();

        return food;
    }

     */
}

