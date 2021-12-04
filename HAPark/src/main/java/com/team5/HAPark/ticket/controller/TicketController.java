package com.team5.HAPark.Ticket.controller;

<<<<<<< HEAD:HAPark/src/main/java/com/team5/HAPark/ticket/TicketController.java
import com.team5.HAPark.Cart.CartSummary;
import com.team5.HAPark.Database.mysql.MySQLDatabase;
import com.team5.HAPark.Ticket.DAO.TicketPersistenceFactory;
=======
import com.team5.HAPark.Cart.model.CartSummary;
import com.team5.HAPark.Ticket.DAO.MySQLTicketPersistence;
import com.team5.HAPark.Ticket.model.Ticket;
import com.team5.HAPark.Ticket.model.TicketOrderItem;
import com.team5.HAPark.Ticket.model.TicketOrderList;
import com.team5.HAPark.Ticket.model.TicketService;
import com.team5.HAPark.database.mysql.MySQLDatabase;
>>>>>>> subfeature_Cart_Summary:HAPark/src/main/java/com/team5/HAPark/ticket/controller/TicketController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.SQLException;
import java.util.List;

@Controller
public class TicketController {

    @Autowired
    private CartSummary cart;

    @GetMapping(value = "/tickets")
    public String displayTickets(Model model) throws SQLException {

        MySQLDatabase dataBase = MySQLDatabase.getInstance();
        TicketService ticketService = new TicketService(new TicketPersistenceFactory().createTicketPersistence());

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
