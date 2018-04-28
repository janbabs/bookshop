package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.service.BookService;
import com.janbabs.bookshop.transport.BookTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String getBooks(Model model) {
        model.addAttribute("lista", bookService.findAll());
        return "books";
    }

    @GetMapping("/{id}")
    public String getBookByID(Model model, @PathVariable("id") Long id) {
        model.addAttribute("book", bookService.findOne(id));
        return "book";
    }

    @GetMapping("/add")
    public String getAddBook(Model model) {
        model.addAttribute("bookTO", new BookTO());
        return "addbook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute @Valid BookTO bookTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addbook";
        }
        bookService.save(bookTO);
        return "redirect:/books";
    }


}
