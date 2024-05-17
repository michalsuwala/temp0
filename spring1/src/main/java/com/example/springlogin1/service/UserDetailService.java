package com.example.springlogin1.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
