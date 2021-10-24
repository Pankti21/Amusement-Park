package com.team5.HAPark;

import database.IUserPersistence;
import database.mysql.MySQLDatabase;
import database.mysql.MySQLUserPersistence;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;


@Controller

public class TestDBController {

    @GetMapping(value = "/testdb") //url to map to
    public String dbTest(Model model) throws SQLException {

        MySQLDatabase db = new MySQLDatabase();
        db.connect();

        //get logged in user's email
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        IUserPersistence userPersistence = new MySQLUserPersistence(db);

        //data added to model will be accessible in html file
        model.addAttribute("email",email);
        model.addAttribute("doesUserExist",userPersistence.doesUserExist(email));
        model.addAttribute("password",userPersistence.getPassword(email));

        db.close();
        return "testui"; //name of html file in resources/templates
    }
}
