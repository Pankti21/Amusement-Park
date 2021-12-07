package com.team5.HAPark.user.controller;

import com.team5.HAPark.user.model.Register;
import com.team5.HAPark.user.model.RegisterUser;
import com.team5.HAPark.user.persistence.IUserPersistence;
import com.team5.HAPark.user.persistence.IUserPersistenceFactory;
import com.team5.HAPark.user.persistence.UserPersistenceFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
class RegisterController {

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("register", new RegisterUser());
        return "Register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute RegisterUser registerUser, Model model) {

        IUserPersistenceFactory factory = new UserPersistenceFactory();
        IUserPersistence userPersistence = factory.createUserPersistence();
        Register register = new Register(registerUser);

        model.addAttribute("register", register.register(userPersistence));
        return "Register";
    }
}
