package com.team5.HAPark;

import com.team5.HAPark.Cart.CartSummary;
import com.team5.HAPark.Food.Food;
import com.team5.HAPark.Ticket.Ticket;
import com.team5.HAPark.Ticket.TicketOrderItem;
import com.team5.HAPark.User.UpdateUserInformation;
import com.team5.HAPark.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@org.springframework.stereotype.Controller
public class UpdateUserInfoController {

    private User user;

    @GetMapping("/updateuserinfo")
    public String allUpdateUser(Model model) throws SQLException {
        model.addAttribute("userFirstName", user.getFirstName());
        model.addAttribute("userLastName", user.getLastName());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userPassword", user.getPassword());
        model.addAttribute("userNewpassword", user.getConfirmedPassword());
        model.addAttribute("userConfirmNewPassword", user.getReconfirmedPassword());
        return "updateuserinformationui";
    }


}