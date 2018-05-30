package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.exceptions.ResourceNotFoundException;
import com.janbabs.bookshop.exceptions.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getErrorPage(Model model, ResourceNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String getEroorPageForWrongLogin(Model model, UserAlreadyExistsException e) {
        model.addAttribute("message", "Użytkownik o loginie: " + e.getMessage() + " istnieje już na naszym portalu.");
        return "error";
    }
}
