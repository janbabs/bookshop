package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.domain.userType;
import com.janbabs.bookshop.service.UserServices;
import com.janbabs.bookshop.transport.UserDTO;
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
        model.addAttribute("userDTO", new UserDTO());
        return "registry";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute @Valid UserDTO userDTO, BindingResult bindingResult) {
        userDTO.setUserType(userType.ADMIN);
        if (bindingResult.hasErrors()) {
            return "registry";
        }
        userDTO.setUserType(userType.USER);
        userServices.save(userDTO);
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
