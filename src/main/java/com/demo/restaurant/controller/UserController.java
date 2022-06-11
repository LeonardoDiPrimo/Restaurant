package com.demo.restaurant.controller;

import com.demo.restaurant.model.User;
import com.demo.restaurant.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

//It is added to the default route for all controllers within the class
@RequestMapping("user/")
public class UserController {

    //Create an instance of the class in which the service logic is located
    @Autowired
    private UserService userService;

    @PostMapping()
    @Operation(summary = "Crear usuario")
    public User createUser(@RequestBody User user) {
        return userService.save(user);
    }

    @GetMapping()
    @Operation(summary = "Listar todos los usuarios")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/validateUser")
    @Operation(summary = "Validar las credenciales del usuario y verificar que no este deprecado")
    public User validateUser(String email, String password) {
        return userService.validateActiveUser(email, password);
    }

    @PutMapping()
    @Operation(summary = "Actualizar usuario")
    public User UpdateUser(User user) {
        return userService.updateUser(user);
    }
}
