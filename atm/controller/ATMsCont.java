package com.atm.controller;

import com.atm.controller.main.Main;
import com.atm.model.ATMs;
import com.atm.model.StatATMs;
import com.atm.model.enums.CurrencyType;
import com.atm.repo.ATMsRepo;
import com.atm.repo.StatATMsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/ATMs")
public class ATMsCont extends Main {

    @Autowired
    private StatATMsRepo statATMsRepo;
    @Autowired
    private ATMsRepo atmsRepo;

    @GetMapping("/{id}")
    public String ATMs(Model model, @PathVariable Long id) {
        addAttributes(model);
        model.addAttribute("currency", CurrencyType.values());
        model.addAttribute("atm", atmsRepo.getReferenceById(id));
        return "ATM";
    }

    //UC-3
    @GetMapping("/{id}/status")
    public String ATMs(@PathVariable Long id) {
        ATMs atm = atmsRepo.getReferenceById(id);
        atm.setStatus(!atm.isStatus());
        atmsRepo.save(atm);
        return "redirect:/ATMs/{id}";
    }

    @PostMapping("/{id}/BYN")
    public String ATM_BYN(Model model, @PathVariable Long id, @RequestParam int BYN) {
        ATMs atm = atmsRepo.getReferenceById(id);

        if (atm.getBYN() + BYN > 100000) {
            model.addAttribute("message", "Превышен лимит пополнения!");
            addAttributes(model);
            model.addAttribute("atm", atmsRepo.getReferenceById(id));
            return "ATM";
        }

        atm.setBYN(atm.getBYN() + BYN);
        atmsRepo.save(atm);

        return "redirect:/ATMs/{id}";
    }

    @PostMapping("/{id}/USD")
    public String ATM_USD(Model model, @PathVariable Long id, @RequestParam int USD) {
        ATMs atm = atmsRepo.getReferenceById(id);

        if (atm.getUSD() + USD > 100000) {
            model.addAttribute("message", "Превышен лимит пополнения!");
            addAttributes(model);
            model.addAttribute("atm", atmsRepo.getReferenceById(id));
            return "ATM";
        }

        atm.setUSD(atm.getUSD() + USD);
        atmsRepo.save(atm);

        return "redirect:/ATMs/{id}";
    }

    @PostMapping("/{id}/EUR")
    public String ATM_EUR(Model model, @PathVariable Long id, @RequestParam int EUR) {
        ATMs atm = atmsRepo.getReferenceById(id);

        if (atm.getEUR() + EUR > 100000) {
            model.addAttribute("message", "Превышен лимит пополнения!");
            addAttributes(model);
            model.addAttribute("atm", atmsRepo.getReferenceById(id));
            return "ATM";
        }

        atm.setEUR(atm.getEUR() + EUR);
        atmsRepo.save(atm);

        return "redirect:/ATMs/{id}";
    }

    @PostMapping("/{id}/RUB")
    public String ATM_RUB(Model model, @PathVariable Long id, @RequestParam int RUB) {
        ATMs atm = atmsRepo.getReferenceById(id);

        if (atm.getRUB() + RUB > 100000) {
            model.addAttribute("message", "Превышен лимит пополнения!");
            addAttributes(model);
            model.addAttribute("atm", atmsRepo.getReferenceById(id));
            return "ATM";
        }

        atm.setRUB(atm.getRUB() + RUB);
        atmsRepo.save(atm);

        return "redirect:/ATMs/{id}";
    }

    @GetMapping("/add")
    public String productAdd(Model model) {
        addAttributes(model);
        return "ATMAdd";
    }

    //UC-2
    @PostMapping("/add")
    public String productAddNew(Model model, @RequestParam String name, @RequestParam float com, @RequestParam String address, @RequestParam String serial, @RequestParam MultipartFile file) {
        ATMs ATMs = new ATMs(name, com, address, serial);

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            String res = "";
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (IOException e) {
                model.addAttribute("message", "Не удалось загрузить изображение");
                addAttributes(model);
                return "ATMAdd";
            }

            ATMs.setFile(res);
        }
        atmsRepo.save(ATMs);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String productEdit(Model model, @PathVariable Long id) {
        addAttributes(model);
        model.addAttribute("atm", atmsRepo.getReferenceById(id));
        return "ATMEdit";
    }

    @PostMapping("/edit/{id}")
    public String productEditOld(Model model, @RequestParam String name, @RequestParam float com, @RequestParam String address, @RequestParam String serial, @RequestParam MultipartFile file, @PathVariable Long id) {
        ATMs ATMs = atmsRepo.getReferenceById(id);
        ATMs.update(name, com, address, serial);

        if (file != null && !Objects.requireNonNull(file.getOriginalFilename()).isEmpty()) {
            String uuidFile = UUID.randomUUID().toString();
            boolean createDir = true;
            String res = "";
            try {
                File uploadDir = new File(uploadImg);
                if (!uploadDir.exists()) createDir = uploadDir.mkdir();
                if (createDir) {
                    res = uuidFile + "_" + file.getOriginalFilename();
                    file.transferTo(new File(uploadImg + "/" + res));
                }
            } catch (IOException e) {
                model.addAttribute("message", "Не удалось загрузить изображение");
                addAttributes(model);
                model.addAttribute("atm", atmsRepo.getReferenceById(id));
                return "ATMEdit";
            }
            ATMs.setFile(res);
        }

        atmsRepo.save(ATMs);

        return "redirect:/ATMs/{id}";
    }

    @GetMapping("/delete/{id}")
    public String productDelete(@PathVariable Long id) {
        atmsRepo.deleteById(id);
        return "redirect:/";
    }
}
