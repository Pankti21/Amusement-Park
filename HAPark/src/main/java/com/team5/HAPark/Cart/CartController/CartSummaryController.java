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
            //List<TicketOrderItem> ticketOrderItemList = cartSummary.getTicket() ;
           // model.addAttribute("cartSummary", cartSummary);
            //List<FoodOrderItem> foodOrderItemList = cartSummary.getFood() ;
            model.addAttribute("cartSummary", cartSummary);
            return "cartsummaryui";
        }
}
