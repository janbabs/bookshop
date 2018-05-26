package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
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
