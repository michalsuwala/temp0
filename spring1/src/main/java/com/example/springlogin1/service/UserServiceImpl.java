package com.example.springlogin1.service;

import com.example.springlogin1.model.Cart;
import com.example.springlogin1.model.Role;
import com.example.springlogin1.model.User;
import com.example.springlogin1.repository.RoleRepository;
import com.example.springlogin1.repository.UserRepositoryCustomImpl;
import com.example.springlogin1.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepositoryCustomImpl userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    public String registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "failure";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCart(new Cart());
        Role userRole =
                roleRepository.findByName("USER").orElse(null);
        if (userRole != null) {
            user.getRoles().add(userRole);
        } else {
            Role role = new Role();
            role.setName("USER");
            user.getRoles().add(role);
            roleRepository.save(role);
        }
        userRepository.save(user);
        return "success";
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Transactional
    public User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        return userRepository.findByUsername(username).orElse(null);


    }
}
