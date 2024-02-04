package com.atm.controller.main;

import com.atm.model.Settings;
import com.atm.model.Users;
import com.atm.model.enums.CurrencyType;
import com.atm.repo.ATMsRepo;
import com.atm.repo.SettingsRepo;
import com.atm.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import java.time.LocalDateTime;

public class Main {

    @Autowired
    protected UsersRepo usersRepo;
    @Autowired
    protected SettingsRepo settingsRepo;
    @Autowired
    protected ATMsRepo atmsRepo;

    @Value("${upload.img}")
    protected String uploadImg;


    protected Users getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((!(auth instanceof AnonymousAuthenticationToken)) && auth != null) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            return usersRepo.findByUsername(userDetail.getUsername());
        }
        return null;
    }

    protected String getUserRole() {
        Users users = getUser();
        if (users == null) return "NOT";
        return users.getRole().name();
    }

    public static float round(float value, int places) {
        if (places < 0) return value;
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (float) tmp / factor;
    }

    protected String getDateNowYearMonthDay() {
        return LocalDateTime.now().toString().substring(0, 10);
    }
    protected Settings getSetting() {
        return settingsRepo.findAll().get(0);
    }

    protected void addAttributes(Model model) {
        model.addAttribute("role", getUserRole());
        model.addAttribute("user", getUser());
    }

    protected void addAttributesATM(Model model, Long atmId) {
        addAttributes(model);
        model.addAttribute("currency", CurrencyType.values());
        model.addAttribute("atm", atmsRepo.getReferenceById(atmId));
    }
}
