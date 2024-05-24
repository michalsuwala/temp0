package com.example.springlogin1.controller;

import com.example.springlogin1.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class GuestBookController {

    @Autowired
    BookService bookService;

    @RequestMapping(path = "/guestbooks", method = RequestMethod.GET)
    public String displayBooks(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        return "guestbooks";
    }
}