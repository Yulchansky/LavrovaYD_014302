package com.atm.controller;

import com.atm.controller.main.Main;
import com.atm.model.*;
import com.atm.model.enums.CurrencyType;
import com.atm.model.enums.Role;
import com.atm.repo.CardsRepo;
import com.atm.repo.HistoriesRepo;
import com.atm.repo.StatATMsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/histories")
public class HistoriesCont extends Main {

    @Autowired
    private HistoriesRepo historiesRepo;
    @Autowired
    private CardsRepo cardsRepo;
    @Autowired
    private StatATMsRepo statATMsRepo;

    @GetMapping
    public String histories(Model model) {
        addAttributes(model);
        if (getUser().getRole() == Role.USER) {
            model.addAttribute("histories", getUser().getHistories());
        } else {
            model.addAttribute("histories", historiesRepo.findAll());
            model.addAttribute("atms", atmsRepo.findAll());
        }
        return "histories";
    }

    @GetMapping("/search/user")
    public String historiesSearch1(Model model, @RequestParam Long cardId, @RequestParam String date) {
        addAttributes(model);
        model.addAttribute("histories", historiesRepo.findAllByCard_IdAndDateContaining(cardId, date));
        model.addAttribute("cardId", cardId);
        model.addAttribute("date", date);
        return "histories";
    }

    @GetMapping("/search/man")
    public String historiesSearch2(Model model, @RequestParam Long atmId, @RequestParam String date) {
        addAttributes(model);
        model.addAttribute("histories", historiesRepo.findAllByATM_IdAndDateContaining(atmId, date));
        model.addAttribute("atmId", atmId);
        model.addAttribute("date", date);
        model.addAttribute("atms", atmsRepo.findAll());
        return "histories";
    }


    //UC-13
    @PostMapping("/add/{atmId}")
    public String historyAdd(Model model, @PathVariable Long atmId, @RequestParam Long cardId, @RequestParam String pin, @RequestParam String cvv, @RequestParam CurrencyType currency, @RequestParam String moneyBYN, @RequestParam String moneyOther) {
        if ((moneyBYN.isEmpty() && currency == CurrencyType.BYN) || (moneyOther.isEmpty() && currency != CurrencyType.BYN)) {
            model.addAttribute("message", "Некорректный ввод суммы!");
            addAttributesATM(model, atmId);
            return "ATM";
        }

        Cards card = cardsRepo.getReferenceById(cardId);
        if (!card.getPin().equals(String.valueOf(pin.hashCode())) || !card.getCvv().equals(String.valueOf(cvv.hashCode()))) {
            model.addAttribute("message", "Некорректный ввод пин-кода или CVV!");
            addAttributesATM(model, atmId);
            return "ATM";
        }

        int byn = 0;
        int other = 0;

        try {
            if (!moneyBYN.isEmpty()) byn = Integer.parseInt(moneyBYN);
            if (!moneyOther.isEmpty()) other = Integer.parseInt(moneyOther);
        } catch (Exception e) {
            model.addAttribute("message", "Некорректный ввод!");
            addAttributesATM(model, atmId);
            return "ATM";
        }

        ATMs atm = atmsRepo.getReferenceById(atmId);
        Users user = getUser();
        Settings s = getSetting();

        float res = 0;

        switch (currency) {
            case BYN -> {
                float com = round(byn * (atm.getCom() / 100), 2);

                switch (card.getCurrency()) {
                    case BYN -> res = com + byn;
                    case USD -> res = com + (byn * s.getBYNtoUSD());
                    case EUR -> res = com + (byn * s.getBYNtoEUR());
                    case RUB -> res = com + (byn * s.getBYNtoRUB());
                }

                if (res > card.getMoney() || byn > atm.getBYN()) {
                    model.addAttribute("message", "Недостаточно средств!");
                    addAttributesATM(model, atmId);
                    return "ATM";
                }
                statATMsRepo.save(new StatATMs(atm, byn, com, currency));

                card.setMoney(card.getMoney() - res);

                atm.setBYN(atm.getBYN() - byn);
            }
            case USD -> {
                float com = round(other * (atm.getCom() / 100), 2);

                switch (card.getCurrency()) {
                    case BYN -> res = com + (other * s.getUSDtoBYN());
                    case USD -> res = com + other;
                    case EUR -> res = com + (other * s.getUSDtoEUR());
                    case RUB -> res = com + (other * s.getUSDtoRUB());
                }

                if (res > card.getMoney() || other > atm.getUSD()) {
                    model.addAttribute("message", "Недостаточно средств!");
                    addAttributesATM(model, atmId);
                    return "ATM";
                }
                statATMsRepo.save(new StatATMs(atm, other, com, currency));

                card.setMoney(card.getMoney() - res);

                atm.setUSD(atm.getUSD() - other);
            }
            case EUR -> {
                float com = round(other * (atm.getCom() / 100), 2);

                switch (card.getCurrency()) {
                    case BYN -> res = com + (other * s.getEURtoBYN());
                    case USD -> res = com + (other * s.getEURtoUSD());
                    case EUR -> res = com + other;
                    case RUB -> res = com + (other * s.getEURtoRUB());
                }

                if (res > card.getMoney() || other > atm.getEUR()) {
                    model.addAttribute("message", "Недостаточно средств!");
                    addAttributesATM(model, atmId);
                    return "ATM";
                }

                statATMsRepo.save(new StatATMs(atm, other, com, currency));

                card.setMoney(card.getMoney() - res);

                atm.setEUR(atm.getEUR() - other);
            }
            case RUB -> {
                float com = round(other * (atm.getCom() / 100), 2);

                switch (card.getCurrency()) {
                    case BYN -> res = com + (other * s.getRUBtoBYN());
                    case USD -> res = com + (other * s.getRUBtoUSD());
                    case EUR -> res = com + (other * s.getRUBtoEUR());
                    case RUB -> res = com + other;
                }

                if (res > card.getMoney() || other > atm.getRUB()) {
                    model.addAttribute("message", "Недостаточно средств!");
                    addAttributesATM(model, atmId);
                    return "ATM";
                }

                statATMsRepo.save(new StatATMs(atm, other, com, currency));

                card.setMoney(card.getMoney() - res);

                atm.setRUB(atm.getRUB() - other);
            }
        }

        historiesRepo.save(new Histories(res,  user, atm, card));

        return "redirect:/ATMs/{atmId}";
    }
}
