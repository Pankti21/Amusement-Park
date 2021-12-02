package com.team5.HAPark.Ticket;

import com.team5.HAPark.Cart.CartSummary;
import com.team5.HAPark.Ticket.DAO.MySQLTicketPersistence;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private CartSummary cart;

    @GetMapping(value = "/tickets")
    public String displayTickets(Model model) throws SQLException {

        MySQLDatabase dataBase = MySQLDatabase.getInstance();
        TicketService ticketService = new TicketService(new MySQLTicketPersistence(dataBase));

        List<Ticket> tickets = ticketService.getAllTickets();

        dataBase.close();

        model.addAttribute("tickets",tickets);

        return "Tickets";
    }

    @PostMapping(value = "/tickets/update")
    public RedirectView addTicketsToCart(@ModelAttribute("ticketOrderList") TicketOrderList ticketOrderList, RedirectAttributes redirectAttributes){

        for(TicketOrderItem ticketOrderItem: ticketOrderList.getTicketOrderList()){

            if (ticketOrderItem.getQuantity()!=null && ticketOrderItem.getQuantity()>0){
                cart.addTicketToCart(ticketOrderItem);
            }
        }
        redirectAttributes.addFlashAttribute("message", "Cart updated");
        return new RedirectView("/tickets");
    }
}
