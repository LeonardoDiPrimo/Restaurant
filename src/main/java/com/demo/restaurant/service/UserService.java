package com.demo.restaurant.service;

import com.demo.restaurant.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

//The service methods are declared but not the implementation
@Service
public interface UserService {
    List<User> findAll();
    User save(User user);
    User validateActiveUser(String email, String password);
    User updateUser(User user);
}
