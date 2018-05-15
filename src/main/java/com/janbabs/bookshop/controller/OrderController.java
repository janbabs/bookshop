package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.domain.Cart;
import com.janbabs.bookshop.domain.Order;
import com.janbabs.bookshop.service.CartService;
import com.janbabs.bookshop.service.OrderService;
import com.janbabs.bookshop.transport.OrderDTO;
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
    private final CartService cartService;

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
        if (!order.getUser().getLogin().equals(currentUserName)) {
            return "error111";
        }
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/make")
    public String getMakeOrderPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Cart cart = cartService.getCartByUserLogin(auth.getName());
        if (cart.isEmpty()){
            return "redirect:/cart";
        }
        model.addAttribute("totalPrice", cart.getTotalPrice());
        return "makeOrder";
    }

    @GetMapping("/add")
    public String getAddOrderPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        model.addAttribute("orderDTO", new OrderDTO());
//        model.addAttribute("cart", cartService.getCartByUserLogin(login));
        return "addorder";
    }

    @PostMapping("/add")
    public String addOrder(@ModelAttribute @Valid OrderDTO orderDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addorder";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName();
        Cart cart = cartService.getCartByUserLogin(login);
        orderService.save(orderDTO, login, cart);
        return "redirect:/order/all";
    }

    @GetMapping("/change/{id}")
    public String changedOrderStatus(@PathVariable("id") Long id, @RequestParam(name = "status") String status) {
        orderService.changeOrderStatus(id, status);
        return "redirect:/order/all";
    }
}