package com.vending.machine.controller;

import com.vending.machine.dto.UserRequest;
import com.vending.machine.dto.UserResponse;
import com.vending.machine.service.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserRequest request) {
        return userService.register(request);
    }


    @GetMapping("/me")
    public UserResponse getCurrentUser(Principal principal) {
        return userService.getCurrentUser(principal.getName());
    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}