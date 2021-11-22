package com.team5.HAPark;

import com.team5.HAPark.Ride.Persistence.RidePersistence;
import com.team5.HAPark.database.mysql.IUserPersistence;
import com.team5.HAPark.database.mysql.MySQLDatabase;
import com.team5.HAPark.database.mysql.MySQLUserPersistence;
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
        RidePersistence ridePersistence = new RidePersistence(new MySQLDatabase());

        //data added to model will be accessible in html file
        model.addAttribute("email",email);
        model.addAttribute("doesUserExist",userPersistence.doesUserExist(email));
        model.addAttribute("password",userPersistence.getPassword(email));
        model.addAttribute("ride_name",ridePersistence.getRide(1));

        db.close();
        return "testui"; //name of html file in resources/templates
    }
}
