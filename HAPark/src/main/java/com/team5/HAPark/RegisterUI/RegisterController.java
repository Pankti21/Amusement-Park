package com.team5.HAPark.RegisterUI;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class RegisterController {

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("register", new Register());
        return "Register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute Register register, Model model) {
        model.addAttribute("register", register);
        return "Result";
    }
}
