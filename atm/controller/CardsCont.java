package com.atm.controller;

import com.atm.controller.main.Main;
import com.atm.model.Cards;
import com.atm.model.enums.CurrencyType;
import com.atm.repo.CardsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cards")
public class CardsCont extends Main {

    @Autowired
    private CardsRepo cardsRepo;

    @GetMapping
    public String cards(Model model) {
        addAttributes(model);
        model.addAttribute("cards", getUser().getCards());
        return "cards";
    }

    @GetMapping("/add")
    public String cardAdd(Model model) {
        addAttributes(model);
        model.addAttribute("currency", CurrencyType.values());
        return "cardAdd";
    }

    @GetMapping("/{id}/delete")
    public String cardDelete(@PathVariable Long id) {
        cardsRepo.deleteById(id);
        return "redirect:/cards";
    }

    @GetMapping("/{id}/status")
    public String cardStatus(@PathVariable Long id) {
        Cards card = cardsRepo.getReferenceById(id);
        card.setStatus(!card.isStatus());
        cardsRepo.save(card);
        return "redirect:/cards";
    }

    @PostMapping("/add")
    public String cardAdd(@RequestParam String name, @RequestParam String number, @RequestParam String cvv, @RequestParam String date, @RequestParam String fio, @RequestParam String pin, @RequestParam float money, @RequestParam CurrencyType currency) {
        cardsRepo.save(new Cards(name, number, date, fio, money, getUser(), String.valueOf(pin.hashCode()), String.valueOf(cvv.hashCode()), currency));
        return "redirect:/cards";
    }

    @PostMapping("/{id}/add")
    public String cardMoneyAdd(@RequestParam float money, @PathVariable Long id) {
        Cards card = cardsRepo.getReferenceById(id);
        card.setMoney(card.getMoney() + money);
        cardsRepo.save(card);
        return "redirect:/cards";
    }
}
