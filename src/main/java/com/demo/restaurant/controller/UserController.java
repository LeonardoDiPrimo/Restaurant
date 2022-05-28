package com.demo.restaurant.controller;

import com.demo.restaurant.exception.ResourceNotFoundException;
import com.demo.restaurant.model.User;
import com.demo.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping()
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/validateUser")
    public User validateUser(String email, String password) {
        return userService.validateActiveUser(email, password);
    }

    @PutMapping("delete/{id}")
    public User deleteUser(@PathVariable(value = "id") Long id) {
        return userService.deleteById(id);
    }

    @PutMapping()
    public User UpdateUser(User user) {
        return userService.updateUser(user);
    }
}
