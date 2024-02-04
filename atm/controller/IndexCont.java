package com.atm.controller;

import com.atm.controller.main.Main;
import com.atm.repo.ATMsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexCont extends Main {
    @GetMapping("/")
    public String index1(Model model) {
        addAttributes(model);
        model.addAttribute("ATMs", atmsRepo.findAll());
        return "index";
    }

    @GetMapping("/search")
    public String indexSearch(Model model, @RequestParam String name, @RequestParam String serial) {
        addAttributes(model);
        model.addAttribute("ATMs", atmsRepo.findAllByNameContainingAndSerialContaining(name, serial));
        model.addAttribute("name", name);
        model.addAttribute("serial", serial);
        return "index";
    }
}
