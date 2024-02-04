package com.atm.controller;

import com.atm.controller.main.Main;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/about")
public class AboutCont extends Main {
    @GetMapping
    public String about(Model model) {
        addAttributes(model);
        model.addAttribute("setting", getSetting());
        return "about";
    }
}
