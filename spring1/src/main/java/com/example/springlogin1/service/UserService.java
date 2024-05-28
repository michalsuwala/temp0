package com.example.springlogin1.service;

import com.example.springlogin1.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    void save(User user);
    public User getCurrentUser();
}