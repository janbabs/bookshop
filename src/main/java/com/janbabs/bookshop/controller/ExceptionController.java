package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String getErrorPage(Model model, ResourceNotFoundException e) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
