package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.domain.Book;
import com.janbabs.bookshop.domain.Cart;
import com.janbabs.bookshop.repository.BookRepository;
import com.janbabs.bookshop.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
        Cart cart = cartService.getCartByUserLogin(auth.getName());
        model.addAttribute("cart", cart);
        model.addAttribute("price", cart.getTotalPrice());
        return "cart";
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long cartId = cartService.getCartByUserLogin(auth.getName()).getId();
        cartService.deleteAll(cartId);
        return "redirect:/cart";
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
        return "redirect:/books";
    }

    @GetMapping("/remove/{bookId}")
    public String removeBook(@PathVariable(name = "bookId") Long bookId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Cart cart = cartService.getCartByUserLogin(auth.getName());
        cartService.removeFromCart(cart.getId(), bookId);
        return "redirect:/cart";
    }

    @GetMapping("/addOne/{cartItemId}")
    public String addOneQuantity(@PathVariable(name = "cartItemId") Long cartItemId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Cart cart = cartService.getCartByUserLogin(auth.getName());
        cartService.editQuantity(cart.getId(), cartItemId, 1);
        return "redirect:/cart";
    }

    @GetMapping("/deleteOne/{cartItemId}")
    public String deleteOneQuantity(@PathVariable(name = "cartItemId") Long cartItemId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Cart cart = cartService.getCartByUserLogin(auth.getName());
        cartService.editQuantity(cart.getId(), cartItemId, -1);
        return "redirect:/cart";
    }
}
