package com.janbabs.bookshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/order_completed")
    public String getThankYouPage() {
        return "thankyou";
    }

    @GetMapping("/403")
    public String getForbiddenErrorPage(Model model) {
        model.addAttribute("message", "Nie masz uprawnień do tej strony!");
        return "error";
    }
}
