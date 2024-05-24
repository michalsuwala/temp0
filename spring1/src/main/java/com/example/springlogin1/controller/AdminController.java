package com.example.springlogin1.controller;

import com.example.springlogin1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @Autowired
    BookService bookService;
    @GetMapping("adminpanel")
    public String adminpanel(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        return "adminpanel";
    }
}
