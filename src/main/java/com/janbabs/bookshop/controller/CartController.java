package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.domain.Book;
import com.janbabs.bookshop.domain.Cart;
import com.janbabs.bookshop.domain.CartItem;
import com.janbabs.bookshop.repository.BookRepository;
import com.janbabs.bookshop.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;
    private final BookRepository bookRepository;

    @GetMapping
    public String getCartPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("cart", cartService.getCartByUserLogin(auth.getName()));
        return "cart";
    }

    @GetMapping("/add/{bookId}")
    public String addBookToCart(@PathVariable(name = "bookId") Long bookId) {
        Book book = null;
        if (bookId != null) {
            book = bookRepository.getOne(bookId);
        }
        if (book != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Cart cart = cartService.getCartByUserLogin(auth.getName());
            cartService.addBook(cart.getId(),book, 1);
        }
        return "redirect:/cart";
    }

    @GetMapping("/remove/{bookId}")
    public String removeBook(@PathVariable(name = "bookId") Long bookId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Cart cart = cartService.getCartByUserLogin(auth.getName());
        cartService.removeFromCart(cart.getId(), bookId);
        return "redirect:/cart";
    }

    @PostMapping("/editquantity")
    public String editquantity(@ModelAttribute Cart cart) {
        return "redirect:/cart";
    }
}
