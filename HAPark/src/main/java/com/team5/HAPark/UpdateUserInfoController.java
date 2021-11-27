package com.team5.HAPark;

import com.team5.HAPark.Cart.CartSummary;
import com.team5.HAPark.Food.Food;
import com.team5.HAPark.Ride.Model.IRideReserveService;
import com.team5.HAPark.Ticket.DAO.MySQLTicketPersistence;
import com.team5.HAPark.Ticket.Ticket;
import com.team5.HAPark.Ticket.TicketOrderItem;
import com.team5.HAPark.Ticket.TicketOrderList;
import com.team5.HAPark.Ticket.TicketService;
import com.team5.HAPark.User.DAO.IUserPersistence;
import com.team5.HAPark.User.DAO.MySQLUserPersistence;
import com.team5.HAPark.User.UpdateUserInformation;
import com.team5.HAPark.User.User;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;

@org.springframework.stereotype.Controller
@Slf4j
public class UpdateUserInfoController {

    private User user;
    private UpdateUserInformation updateUserInformation;
    private MySQLUserPersistence mySQLUserPersistence;
    private IUserPersistence iUserPersistence;

    @GetMapping("/updateUserInfo")
    public String allUpdateUser(Model model) throws SQLException {
        UpdateUserInformation updateUserInformation = new UpdateUserInformation(user);

        model.addAttribute("userFirstName", user.getFirstName());
        model.addAttribute("userLastName", user.getLastName());
        model.addAttribute("userEmail", user.getEmail());
        model.addAttribute("userPassword", user.getPassword());
        model.addAttribute("userNewPassword", user.getConfirmedPassword());
        model.addAttribute("userConfirmNewPassword", user.getReconfirmedPassword());
        return "updateuserinformationui";
    }

    @PostMapping(value = "/updateUserInfo/update")
    public RedirectView updateUserInfo(Model model,@ModelAttribute("update") User user) throws SQLException, NoSuchAlgorithmException {
        MySQLDatabase dataBase = new MySQLDatabase();
        MySQLUserPersistence mySQLUserPersistence = new MySQLUserPersistence(dataBase);
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        log.info("{}",username);
        log.info("{}first name {} last name {} email {} password {} confirm password {} reconfirm password ",
                user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getConfirmedPassword(),user.getReconfirmedPassword());
        updateUserInformation.updateUserPassword(iUserPersistence,user.getConfirmedPassword(),user.getReconfirmedPassword());
        //Saves to database
        mySQLUserPersistence.userUpdatedPassword(user.getEmail(),user.getConfirmedPassword());
        model.addAttribute("userEmail",user.getEmail());
        model.addAttribute("userNewPassword", user.getConfirmedPassword());

        return new RedirectView("/updateUserInfo");
    }


}