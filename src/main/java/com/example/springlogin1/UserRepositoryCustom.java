package com.example.springlogin1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserRepositoryCustom extends Repository<User, Long> {
    Optional<User> findByUsername(String username);
    User save(User user);

}