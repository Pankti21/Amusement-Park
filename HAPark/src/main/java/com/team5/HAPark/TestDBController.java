package com.team5.HAPark;

import database.IUserPersistence;
import database.mysql.MySQLDatabase;
import database.mysql.MySQLUserPersistence;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;


@Controller

public class TestDBController {

    @GetMapping(value = "/testdb") //url to map to
    public String dbTest(Model model) {

        MySQLDatabase db = new MySQLDatabase();
        db.connect();

        IUserPersistence userPersistence = new MySQLUserPersistence(db);
        String email = "a.robertson@gmail.com";

        //data added to model will be accessible in html file
        model.addAttribute("email",email);
        model.addAttribute("doesUserExist",userPersistence.doesUserExist(email));
        model.addAttribute("password",userPersistence.getPassword(email));

        db.close();
        return "testui"; //name of html file in resources/templates
    }
}
