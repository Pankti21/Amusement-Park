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

    private User user = new User();
    private UpdateUserInformation updateUserInformation;
    private MySQLDatabase dataBase = new MySQLDatabase();
    private MySQLUserPersistence mySQLUserPersistence = new MySQLUserPersistence(dataBase);
    private IUserPersistence iUserPersistence= new MySQLUserPersistence(dataBase);

    @GetMapping("/updateuserinfo")
    public String allUpdateUser(Model model) throws SQLException {
        UpdateUserInformation updateUserInformation = new UpdateUserInformation(user);
        return "updateuserinformationui";
    }

    @PostMapping(value = "/updateUserInfo/update")
    public RedirectView updateUserInfo(@ModelAttribute("update") User user) throws SQLException, NoSuchAlgorithmException {

        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        log.info("{}",username);
        log.info("{}first name {} last name {} email {} old password {} confirm password {} reconfirm password ",
                user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getConfirmedPassword(),user.getReconfirmedPassword());
        updateUserInformation.updateUserPassword(iUserPersistence,user.getPassword(),user.getConfirmedPassword(),user.getReconfirmedPassword());


        return new RedirectView("/updateuserinfo");
    }


}