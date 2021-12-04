package com.team5.HAPark.user;

import com.team5.HAPark.database.mysql.MySQLDatabase;
import com.team5.HAPark.user.DAO.IUserPersistence;
import com.team5.HAPark.user.DAO.MySQLUserPersistence;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@org.springframework.stereotype.Controller
@Slf4j
public class UpdateUserInfoController {

    private User user = new User();
    private UpdateUserInformation updateUserInformation;
    private MySQLDatabase dataBase = MySQLDatabase.getInstance();
    private MySQLUserPersistence mySQLUserPersistence = new MySQLUserPersistence(dataBase);
    private IUserPersistence iUserPersistence= new MySQLUserPersistence(dataBase);

    @GetMapping("/updateuserinfo")
    public String allUpdateUser(Model model) throws SQLException {
        model.addAttribute("user", new User());
        return "UpdateUserInfo";
    }

    @PostMapping("/updateuserinfo")
    public String updateUserInfo(@ModelAttribute("user") User user) throws SQLException, NoSuchAlgorithmException {
        UpdateUserInformation updateUserInformation2 = new UpdateUserInformation(user);
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        log.info("{}",username);
        log.info("{} firstname {} lastname {} email {} oldpassword {} confirmedpassword {} reconfirmpassword ",
                user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getConfirmedPassword(),user.getReconfirmedPassword());
        updateUserInformation2.updateUserPassword(iUserPersistence,user.getPassword(),user.getConfirmedPassword(),user.getReconfirmedPassword());

        return "UpdateUserInfo";
    }

}
