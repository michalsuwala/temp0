package com.example.springlogin1.controller;

import com.example.springlogin1.service.UserServiceImpl;
import com.example.springlogin1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        String result = userService.registerUser(user);
        if ("success".equals(result)) {
            return "redirect:/login?registerSuccess";
        } else {
            model.addAttribute("errorMessage", "Username already exists!");
            return "register";
        }
    }
}
