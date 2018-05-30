package com.janbabs.bookshop.controller;

import com.janbabs.bookshop.domain.User;
import com.janbabs.bookshop.domain.userType;
import com.janbabs.bookshop.service.UserService;
import com.janbabs.bookshop.transport.UserDTO;
import com.janbabs.bookshop.transport.UserEditDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/add")
    public String getRegistryPage(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "registry";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute @Valid UserDTO userDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registry";
        }
        userDTO.setUserType(userType.USER);
        userService.save(userDTO);
        return "redirect:/login";
    }

    @GetMapping("/all")
    public String getUsersPage(Model model){
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/promote/{id}")
    public String promoteToAdmin(@PathVariable(name = "id") Long id) {
        userService.promoteToAdmin(id);
        return "redirect:/user/all";
    }

    @GetMapping("/change/{id}")
    public String getEditUserPageById (@PathVariable(name = "id") Long id, Model model){
        model.addAttribute("userEditDTO", userService.findUserEditDTOById(id));
        return "edituser";
    }
    @PostMapping("/change/{id}")
    public String editUserById(@ModelAttribute @Valid UserEditDTO userEditDTO,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edituser";
        }
        userService.update(userEditDTO);
        return "redirect:/user/all";
    }

    @GetMapping("/change")
    public String getEditUserPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("userEditDTO", userService.getUserIDTOLogin(auth.getName()));
        return "editInfo";
    }
    @PostMapping("/change")
    public String editUserBy(@ModelAttribute @Valid UserEditDTO userEditDTO,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "editInfo";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!userEditDTO.getLogin().equals(auth.getName()))
            return "redirect:/403";
        userService.update(userEditDTO);
        return "redirect:/";
    }
}
