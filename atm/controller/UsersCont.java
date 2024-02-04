package com.atm.controller;

import com.atm.controller.main.Main;
import com.atm.model.Users;
import com.atm.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UsersCont extends Main {
    @GetMapping
    public String Users(Model model) {
        addAttributes(model);
        model.addAttribute("actual", getUser());
        model.addAttribute("users", usersRepo.findAll());
        model.addAttribute("roles", Role.values());
        return "users";
    }

    @PostMapping("/update/{id}")
    public String UserUpdate(@PathVariable Long id, @RequestParam Role role) {
        Users users = usersRepo.getReferenceById(id);
        users.setRole(role);
        usersRepo.save(users);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String UserDelete(@PathVariable Long id) {
        usersRepo.deleteById(id);
        return "redirect:/users";
    }


}
