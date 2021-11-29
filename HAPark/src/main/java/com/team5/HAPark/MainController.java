package com.team5.HAPark;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/Main")
    public String welcome() {
        return "MainPage";
    }
}
