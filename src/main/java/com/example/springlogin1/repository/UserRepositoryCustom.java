package com.example.springlogin1.repository;

import com.example.springlogin1.model.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepositoryCustom extends Repository<User, Long> {
    Optional<User> findByUsername(String username);
    User save(User user);
    void delete(Long id);

}

