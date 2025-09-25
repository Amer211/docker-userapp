package com.example.demo.service;

import com.example.demo.user.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(User user);
    User getUserById(Long id);
}
