package com.team5.HAPark;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping("/Welcome")
    public String welcome() {
        return "Welcome";
    }
}
