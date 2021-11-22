package com.team5.HAPark.User.UserController;

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

    @Autowired
    //private UpdateUserInformation updateUserInformation;
    private User user;

    @GetMapping("/updateuserinfo")
    public String allUpdateUser(Model model) throws SQLException {
        model.addAttribute("allUserInfo", user.getFirstName());
        model.addAttribute("allUserInfo", user.getLastName());
        model.addAttribute("allUserInfo", user.getEmail());
        model.addAttribute("allUserInfo", user.getPassword());
        model.addAttribute("allUserInfo", user.getConfirmedPassword());
        model.addAttribute("allUserInfo", user.getReconfirmedPassword());
        return "updateuserinformationui";
    }


}