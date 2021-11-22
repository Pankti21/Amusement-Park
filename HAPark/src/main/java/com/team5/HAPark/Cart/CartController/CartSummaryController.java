package com.team5.HAPark.Cart.CartController;

import com.team5.HAPark.Food.Food;
import com.team5.HAPark.Food.FoodOrderItem;
import com.team5.HAPark.Ride.Model.RideService;
import com.team5.HAPark.Ticket.Ticket;
import com.team5.HAPark.Ticket.TicketOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

import com.team5.HAPark.Cart.CartSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;
import java.util.Arrays;

    @org.springframework.stereotype.Controller
    public class CartSummaryController {

        @Autowired
        private CartSummary cartSummary;
        private TicketOrderItem ticketOrderItem;
        private Ticket ticket;
        private Food food;

        @GetMapping("/cartsummary")
        public String allTicketItems(Model model) throws SQLException {
            model.addAttribute("allTickets", ticket.getTicketType());
            model.addAttribute("allTickets", ticket.getTicketType());
            model.addAttribute("allTickets", ticketOrderItem.getQuantity());
            return "cartsummaryui";
        }

        @GetMapping("/cartsummary")
        public String allFoodItems(Model model) throws SQLException {
            model.addAttribute("allFood", food.getId());
            model.addAttribute("allFood", food.getQuantity());
            model.addAttribute("allFood", food.getPrice());
            return "cartsummaryui";
        }
}
