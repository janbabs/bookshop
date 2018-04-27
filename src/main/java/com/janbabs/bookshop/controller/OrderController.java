package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.service.OrderService;
import com.janbabs.bookshop.transport.OrderTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/add/{id}")
    public String getAddOrderPage(OrderTO orderTO, Model model) {
        model.addAttribute("orderTO", orderTO);
        return "addorder";
    }

    @PostMapping("/add/{id}")
    public String addOrder(@ModelAttribute @Valid OrderTO orderTO, BindingResult bindingResult, @PathVariable("id") Long bookId)

    {
        if (bindingResult.hasErrors()) {
            return "addorder";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        orderService.save(orderTO, login, bookId);
        return "pages/order";
    }
}
