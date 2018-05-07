package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.service.BookService;
import com.janbabs.bookshop.transport.BookDTO;
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
        model.addAttribute("bookDTO", new BookDTO());
        return "addbook";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute @Valid BookDTO bookDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addbook";
        }
        bookService.save(bookDTO);
        return "redirect:/books";
    }

    @GetMapping("/change/{id}")
    public String getEditBookPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("bookDTO", bookService.findOne(id));
        return "editbook";
    }

    @PostMapping("change/{id}")
    public String editBook(@PathVariable("id") Long id, @ModelAttribute @Valid BookDTO bookDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editbook";
        }
        bookService.update(bookDTO);
        return "redirect:/books/{id}";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
        return "redirect:/books";
    }
}
