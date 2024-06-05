package com.example.springlogin1.repository;

import com.example.springlogin1.model.Order;
import com.example.springlogin1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
