package com.team5.HAPark.user;

import com.team5.HAPark.user.DAO.IUserPersistence;
import com.team5.HAPark.user.DAO.UserPersistenceFactory;
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

    @GetMapping("/updateuserinfo")
    public String allUpdateUser(Model model) throws SQLException {
        model.addAttribute("user", new UpdateableUser());
        return "UpdateUserInfo";
    }

    @PostMapping("/updateuserinfo")
    public String updateUserInfo(@ModelAttribute("user") UpdateableUser user) throws SQLException, NoSuchAlgorithmException {
        UpdateUserInformation updateUserInformation = new UpdateUserInformation(user);
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        log.info("{}",username);
        log.info("{} email {} oldpassword {} newpassword {} confirmedpassword ",
                user.getEmail(),user.getOldPassword(),user.getPassword(),user.getConfirmedPassword());

        UserPersistenceFactory userPersistenceFactory = new UserPersistenceFactory();
        IUserPersistence userPersistence = userPersistenceFactory.createUserPersistence();
        updateUserInformation.updateUserPassword(userPersistence,user);

        return "UpdateUserInfo";
    }

}
