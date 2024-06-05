package com.example.springlogin1.controller;

import com.example.springlogin1.model.Order;
import com.example.springlogin1.model.User;
import com.example.springlogin1.service.OrderService;
import com.example.springlogin1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;
    @PostMapping("/submit")
    public String submitOrder() {
        Order order = orderService.submitOrder();
        return "redirect:/order/" + order.getId();
    }
    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable Long orderId, Model model) {
        Order order = orderService.getOrder(orderId);
        model.addAttribute("order", order);
        return "order";
    }

    @GetMapping("/history")
    public String getOrderHistory(Model model, Principal principal) {
        User currentUser = userService.getCurrentUser();
        List<Order> orders = orderService.getUserOrders(currentUser);
        model.addAttribute("orders", orders);
        return "order_history";
    }
}
