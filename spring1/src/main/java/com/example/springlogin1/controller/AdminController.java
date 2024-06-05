package com.example.springlogin1.controller;

import com.example.springlogin1.model.Order;
import com.example.springlogin1.service.BookService;
import com.example.springlogin1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    BookService bookService;

    @Autowired
    OrderService orderService;
    @GetMapping("adminpanel")
    public String adminpanel(Model model) {
        model.addAttribute("books", this.bookService.getAll());
        model.addAttribute("orders", this.orderService.getAllOrders());
        return "adminpanel";
    }


    @PostMapping("adminpanel/updateOrderStatus")
    public String updateOrderStatus(@RequestParam Long orderId, @RequestParam Order.OrderStatus status) {
        orderService.updateOrderStatus(orderId, status);
        return "redirect:/adminpanel";
    }
}
