package com.team5.HAPark;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WelcomeController {

    @RequestMapping("/welcome")
    public String welcome(ModelAndView modelAndView) {

        return "welcomeui";
        //fix logic here don't show to logged in users
        //check login/out button dispaly
        //add header to all pages
        //check links and program flow
    }

    public String welcome() {
        return "welcomeui";
    }

    @RequestMapping("/")
    public RedirectView directHomeToWelcome() {
        RedirectView redirectView;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        if (name.equals("anonymousUser")) {
            redirectView = new RedirectView("/welcome");
        } else {
            redirectView = new RedirectView("/main");
        }
        return redirectView;
    }

}
