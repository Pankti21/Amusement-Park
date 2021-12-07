package com.team5.HAPark.user.controller;

import com.team5.HAPark.user.model.IUpdateUserInformation;
import com.team5.HAPark.user.model.UpdateUserInformation;
import com.team5.HAPark.user.persistence.IUserPersistence;
import com.team5.HAPark.user.persistence.UserPersistenceFactory;
import com.team5.HAPark.user.model.UpdateableUser;
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
        IUpdateUserInformation updateUserInformation = new UpdateUserInformation(user);
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        log.info("{}",username);
        log.info("{} email {} oldpassword {} newpassword {} confirmedpassword ",
                user.getEmail(),user.getOldPassword(),user.getPassword(),user.getConfirmedPassword());

        UserPersistenceFactory userPersistenceFactory = new UserPersistenceFactory();
        IUserPersistence userPersistence = userPersistenceFactory.createUserPersistence();
        updateUserInformation.updateUserPassword(userPersistence);

        return "UpdateUserInfo";
    }

}
