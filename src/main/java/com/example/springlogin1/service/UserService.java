package com.example.springlogin1;

import com.example.springlogin1.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);
    User save(User user);
}