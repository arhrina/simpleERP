package com.motok.motoK.account.ui;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class AccountController {
    @GetMapping("/view/account")
    public String viewAccount(@RequestParam(defaultValue = "") String date, Model model) {
        if (date.isEmpty()) date = LocalDate.now().toString();

        model.addAttribute("date", date);
        return "account/account";
    }
}
