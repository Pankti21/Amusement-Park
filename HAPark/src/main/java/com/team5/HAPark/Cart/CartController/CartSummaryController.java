package com.team5.HAPark.Cart.CartController;

import com.team5.HAPark.Food.Food;
import com.team5.HAPark.Ticket.Ticket;
import com.team5.HAPark.Ticket.TicketOrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

import com.team5.HAPark.Cart.CartSummary;

    @org.springframework.stereotype.Controller
    public class CartSummaryController {

        @Autowired
        private CartSummary cartSummary;
        private TicketOrderItem ticketOrderItem;
        private Ticket ticket;
        private Food food;

        @GetMapping("/cartsummary")
        public String allCartItems(Model model) throws SQLException {
            model.addAttribute("ticketType", ticket.getTicketType());
            model.addAttribute("ticketQuantity", ticketOrderItem.getQuantity());
            model.addAttribute("ticketPrice", ticket.getTicketPrice());
            model.addAttribute("foodId", food.getId());
            model.addAttribute("foodQuantity", food.getQuantity());
            model.addAttribute("foodPrice", food.getPrice());
            return "cartsummaryui";
        }
}
