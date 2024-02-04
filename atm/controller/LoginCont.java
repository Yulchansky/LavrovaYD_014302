package com.atm.controller;

import com.atm.controller.main.Main;
import com.atm.model.Settings;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginCont extends Main {
    @GetMapping
    public String login(Model model) {
        addAttributes(model);
        if (settingsRepo.findAll().isEmpty()) {
            settingsRepo.save(new Settings());
        }
        return "login";
    }
}
