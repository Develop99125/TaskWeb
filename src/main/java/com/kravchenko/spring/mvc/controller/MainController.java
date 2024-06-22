package com.kravchenko.spring.mvc.controller;

import com.kravchenko.spring.mvc.entity.User;
import com.kravchenko.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    public String getLoginPage(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "login_page";
    }

    @RequestMapping("/register")
    public String createUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registration_page";
    }

    @RequestMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "registration_page";
        }
        boolean result = userService.createNewUser(user);
        if(!result) {
            model.addAttribute("errorLog", "User with this login already exists.");
            return "registration_page";
        }
        return "redirect:/login";
    }
}
