package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.domain.userType;
import com.janbabs.bookshop.service.UserServices;
import com.janbabs.bookshop.transport.UserTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/add")
    public String getRegistryPage(Model model) {
        model.addAttribute("userTO", new UserTO());
        return "registry";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute @Valid UserTO userTO, BindingResult bindingResult) {
        userTO.setUserType(userType.ADMIN);
        if (bindingResult.hasErrors()) {
            return "registry";
        }
        userTO.setUserType(userType.USER);
        userServices.save(userTO);
        return "redirect:/login";
    }

    @GetMapping("/all")
    public String getUsersPage(Model model){
        model.addAttribute("users", userServices.findAll());
        return "users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        userServices.delete(id);
        return  "redirect:/user/all";
    }

    @GetMapping("/promote/{id}")
    public String promoteToAdmin(@PathVariable(name = "id") Long id) {
        userServices.promoteToAdmin(id);
        return "redirect:/user/all";
    }
}
