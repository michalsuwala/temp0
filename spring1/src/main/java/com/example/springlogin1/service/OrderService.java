package com.example.springlogin1.service;

import com.example.springlogin1.model.*;
import com.example.springlogin1.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private BookService bookService;

    @Transactional
    public Order submitOrder() {
        User user = userService.getCurrentUser();
        Cart cart = user.getCart();
        Order order = new Order();
        order.setDate(new Date());
        order.setStatus(Order.OrderStatus.SUBMITTED);
        order.setUser(user);
        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getQuantity() > cartItem.getBook().getQuantity()) {
                throw new RuntimeException("Not enough stock available for book: " + cartItem.getBook().getTitle());
            }
            cartItem.getBook().setQuantity(cartItem.getBook().getQuantity() - cartItem.getQuantity());
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setQuantity(cartItem.getQuantity());
            order.getItems().add(orderItem);
        }

        cart.getItems().clear();
        cartService.saveCart(cart);
        return orderRepository.save(order);
    }


    @Transactional
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(()
                -> new RuntimeException("Order not found"));
    }
    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Transactional
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void updateOrderStatus(Long orderId, Order.OrderStatus status) {
        Order order = getOrder(orderId);
        order.setStatus(status);
        orderRepository.save(order);
    }

    @Transactional
    public List<Order> getUserOrders(User user) {
        return orderRepository.findByUser(user);
    }

    @Transactional
    public void deleteBooksWithZeroQuantity() {
        List<Book> books = bookService.getAll();
        for (Book book : books) {
            if (book.getQuantity() == 0) {
                List<Order> orders = orderRepository.findAll();
                for (Order order : orders) {
                    List<OrderItem> orderItems = order.getItems();
                    orderItems.removeIf(orderItem -> orderItem.getBook().getId() == book.getId());
                }

                for (Order order : orders) {
                    orderRepository.save(order);
                }

                bookService.delete(book.getId());
            }
        }
    }
}
