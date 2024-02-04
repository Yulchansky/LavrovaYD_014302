package com.atm.controller;

import com.atm.controller.main.Main;
import com.atm.model.ATMs;
import com.atm.model.Histories;
import com.atm.model.StatATMs;
import com.atm.model.enums.CurrencyType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/stats")
public class StatsCont extends Main {
    @GetMapping
    public String Stat(Model model) {
        addAttributes(model);

        List<ATMs> atms = atmsRepo.findAll();

        atms.sort(Comparator.comparing(ATMs::getTransactions));
        Collections.reverse(atms);

        model.addAttribute("atms", atms);

        String[] stringTransactions = new String[5];
        int[] intTransactions = new int[5];

        int index = 0;
        for (ATMs i : atms) {
            if (index == 5) break;
            stringTransactions[index] = i.getName();
            intTransactions[index] = i.getTransactions();
            index++;
        }

        model.addAttribute("stringTransactions", stringTransactions);
        model.addAttribute("intTransactions", intTransactions);

        CurrencyType[] currency = CurrencyType.values();
        String[] stringCurrency = new String[currency.length];
        int[] intCurrencyTransactions = new int[currency.length];

        for (int i = 0; i < currency.length; i++) {
            stringCurrency[i] = currency[i].getName();
            for (ATMs a : atms) {
                for (StatATMs s : a.getStatATMs()) {
                    if (currency[i] == s.getType()) {
                        intCurrencyTransactions[i]++;
                    }
                }
            }
        }

        model.addAttribute("stringCurrency", stringCurrency);
        model.addAttribute("intCurrencyTransactions", intCurrencyTransactions);

        int[] intCurrency = new int[currency.length];
        float[] intCurrencyCom = new float[currency.length];

        for (int i = 0; i < currency.length; i++) {
            for (ATMs a : atms) {
                for (StatATMs s : a.getStatATMs()) {
                    if (currency[i] == s.getType()) {
                        intCurrency[i] += s.getSum();
                        intCurrencyCom[i] += s.getCom();
                    }
                }
            }
        }

        model.addAttribute("intCurrency", intCurrency);
        model.addAttribute("intCurrencyCom", intCurrencyCom);

        return "stats";
    }

    //UC-5
    @GetMapping("/filter")
    public String StatFilter(Model model, @RequestParam String date) {
        addAttributes(model);

        model.addAttribute("date", date);

        List<ATMs> atms = atmsRepo.findAll();

        atms.sort(Comparator.comparing(ATMs::getTransactions));
        Collections.reverse(atms);

        model.addAttribute("atms", atms);

        String[] stringTransactions = new String[5];
        int[] intTransactions = new int[5];

        int index = 0;
        for (ATMs i : atms) {
            if (index == 5) break;
            stringTransactions[index] = i.getName();
            intTransactions[index] = i.getTransactions(date);
            index++;
        }

        model.addAttribute("stringTransactions", stringTransactions);
        model.addAttribute("intTransactions", intTransactions);

        CurrencyType[] currency = CurrencyType.values();
        String[] stringCurrency = new String[currency.length];
        int[] intCurrencyTransactions = new int[currency.length];

        for (int i = 0; i < currency.length; i++) {
            stringCurrency[i] = currency[i].getName();
            for (ATMs a : atms) {
                for (StatATMs s : a.getStatATMs()) {
                    if (currency[i] == s.getType() && s.getDate().equals(date)) {
                        intCurrencyTransactions[i]++;
                    }
                }
            }
        }

        model.addAttribute("stringCurrency", stringCurrency);
        model.addAttribute("intCurrencyTransactions", intCurrencyTransactions);

        int[] intCurrency = new int[currency.length];
        float[] intCurrencyCom = new float[currency.length];

        for (int i = 0; i < currency.length; i++) {
            for (ATMs a : atms) {
                for (StatATMs s : a.getStatATMs()) {
                    if (currency[i] == s.getType() && s.getDate().equals(date)) {
                        intCurrency[i] += s.getSum();
                        intCurrencyCom[i] += s.getCom();
                    }
                }
            }
        }

        model.addAttribute("intCurrency", intCurrency);
        model.addAttribute("intCurrencyCom", intCurrencyCom);

        return "stats";
    }
}
