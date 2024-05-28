package com.example.springlogin1.service;

import com.example.springlogin1.dao.IBookDAO;
import com.example.springlogin1.model.Book;
import com.example.springlogin1.model.Cart;
import com.example.springlogin1.model.User;
import com.example.springlogin1.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private IBookDAO bookRepository;
    @Autowired
    private UserService userService;

    @Transactional
    public Cart getCart() {
        User user = userService.getCurrentUser();
        return user.getCart();
    }

    @Transactional
    public Cart addToCart(int bookId, int quantity) {
        Cart cart = getCart();
        Book book = bookRepository.getById(bookId).orElseThrow(()
                -> new RuntimeException("Book not found"));
        cart.addItem(book, quantity);
        return saveCart(cart);
    }

    @Transactional
    public Cart removeFromCart(int bookId, int quantity) {
        Cart cart = getCart();
        Book book = bookRepository.getById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
        cart.removeItem(book, quantity);
        return saveCart(cart);
    }

    @Transactional
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }
}
