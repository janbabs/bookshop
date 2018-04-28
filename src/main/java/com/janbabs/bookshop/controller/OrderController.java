package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.domain.Order;
import com.janbabs.bookshop.service.BookService;
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
    private final BookService bookService;

    @GetMapping("/all")
    public String getOrdersPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        model.addAttribute("orderlist", orderService.findByLogin(login));
        return "orders";
    }

    @GetMapping("/{id}")
    public String getOrderPage(@PathVariable("id") Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = auth.getName();
        Order order = orderService.findById(id);
        if (order.getUser().getLogin() != currentUserName) {
            return "error";
        }
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/add/{id}")
    public String getAddOrderPage(Model model, @PathVariable("id") Long bookId) {
        model.addAttribute("orderTO", new OrderTO());
        model.addAttribute("bookTO", bookService.findOne(bookId));
        return "addorder";
    }

    @PostMapping("/add/{id}")
    public String addOrder(@ModelAttribute @Valid OrderTO orderTO, BindingResult bindingResult, @PathVariable("id") Long bookId) {
        if (bindingResult.hasErrors()) {
            return "addorder";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        orderService.save(orderTO, login, bookId);
        return "redirect:/order/all";
    }

    @GetMapping("/change/{id}")
    public String changedOrderStatus(@PathVariable("id") Long id, @RequestParam(name = "status") String status) {
        orderService.changeOrderStatus(id, status);
        return "redirect:/order/all";
    }
}
