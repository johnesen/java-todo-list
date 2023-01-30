package com.todoapp.project.controller;


import com.todoapp.project.entity.User;
import com.todoapp.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*; // importing GetMapping, PostMapping

import java.util.*;

@RestController
@CrossOrigin(origins = {"http://localhost;3000"})
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    private User getCurrentUser(@RequestBody User user) {
        System.out.println("GET User by username and password *****");
        return userService.getUser(user);
    }

    @GetMapping("/login/{username}/{password}")
    private boolean findUserByUsername(@PathVariable String username, @PathVariable String password) {
        System.out.println("GET User by username and password *****");
        return userService.getUserByUsername(username, password);
    }

    @PostMapping("/createUser")
    private HashMap<String, String> addUser(@RequestBody User user) {
        boolean user_exits = userService.findUserByUsername(user.getUsername());
        if(user_exits) {
            System.out.println("CANT CREATE USER!");
            HashMap<String, String> existData = new HashMap<String, String>();
            existData.put("detail", "user with this username already exist");
            return existData;
        }
        int password_length = user.getPassword().length();
        if(password_length < 8) {
            HashMap<String, String> passwordErrorData = new HashMap<String, String>();
            passwordErrorData.put("detail", "Your password too short, min 8");
            return passwordErrorData;
        }
        userService.saveUser(user);
        HashMap<String, String> registerData = new HashMap<String, String>();
        registerData.put("detail", "You successfully created user");
        return registerData;
    }
}
