package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.domain.Book;
import com.janbabs.bookshop.domain.Cart;
import com.janbabs.bookshop.domain.CartItem;
import com.janbabs.bookshop.service.BookService;
import com.janbabs.bookshop.service.CartService;
import com.janbabs.bookshop.transport.BookDTO;
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
    private final BookService bookService;

    @GetMapping
    public String getCartPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("cart", cartService.getCartByUserLogin(auth.getName()));
        return "cart";
    }

    @GetMapping("/add/{bookId}")
    public String addBookToCart(@PathVariable(name = "bookId") Long bookId) {
        BookDTO bookDTO = null;
        if (bookId != null) {
            bookDTO = bookService.findOne(bookId);
        }
        if (bookDTO != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Cart cart = cartService.getCartByUserLogin(auth.getName());
            Book book = bookService.convertToBook(bookDTO);
            CartItem cartItem = new CartItem(book, 1);
        }
        return "redirect:/cart";
    }
}
