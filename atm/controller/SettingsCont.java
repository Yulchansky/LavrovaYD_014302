package com.atm.controller;

import com.atm.controller.main.Main;
import com.atm.model.Settings;
import com.atm.model.enums.CurrencyType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/settings")
public class SettingsCont extends Main {
    @GetMapping
    public String settings(Model model) {
        addAttributes(model);
        model.addAttribute("setting", getSetting());
        model.addAttribute("currency", CurrencyType.values());
        return "settings";
    }

    //UC-4
    @PostMapping("/limit")
    public String settingLimit(@RequestParam CurrencyType currency, @RequestParam int BYN, @RequestParam int USD, @RequestParam int EUR, @RequestParam int RUB) {
        Settings setting = getSetting();

        switch (currency) {
            case BYN -> setting.setLimitBYN(BYN);
            case USD -> setting.setLimitUSD(USD);
            case EUR -> setting.setLimitEUR(EUR);
            case RUB -> setting.setLimitRUB(RUB);
        }

        settingsRepo.save(setting);
        return "redirect:/settings";
    }

    @PostMapping("/BYNto")
    public String settingBYNto(@RequestParam CurrencyType currency, @RequestParam float USD, @RequestParam float EUR, @RequestParam float RUB) {
        Settings setting = getSetting();

        switch (currency) {
            case USD -> setting.setBYNtoUSD(USD);
            case EUR -> setting.setBYNtoEUR(EUR);
            case RUB -> setting.setBYNtoRUB(RUB);
        }

        settingsRepo.save(setting);
        return "redirect:/settings";
    }

    @PostMapping("/USDto")
    public String settingUSDto(@RequestParam CurrencyType currency, @RequestParam float BYN, @RequestParam float EUR, @RequestParam float RUB) {
        Settings setting = getSetting();

        switch (currency) {
            case BYN -> setting.setUSDtoBYN(BYN);
            case EUR -> setting.setUSDtoEUR(EUR);
            case RUB -> setting.setUSDtoRUB(RUB);
        }

        settingsRepo.save(setting);
        return "redirect:/settings";
    }

    @PostMapping("/EURto")
    public String settingEURto(@RequestParam CurrencyType currency, @RequestParam float BYN, @RequestParam float USD, @RequestParam float RUB) {
        Settings setting = getSetting();

        switch (currency) {
            case BYN -> setting.setEURtoBYN(BYN);
            case USD -> setting.setEURtoUSD(USD);
            case RUB -> setting.setEURtoRUB(RUB);
        }

        settingsRepo.save(setting);
        return "redirect:/settings";
    }

    @PostMapping("/RUBto")
    public String settingRUBto(@RequestParam CurrencyType currency, @RequestParam float BYN, @RequestParam float USD, @RequestParam float EUR) {
        Settings setting = getSetting();

        switch (currency) {
            case BYN -> setting.setRUBtoBYN(BYN);
            case USD -> setting.setRUBtoUSD(USD);
            case EUR -> setting.setRUBtoEUR(EUR);
        }

        settingsRepo.save(setting);
        return "redirect:/settings";
    }
}
