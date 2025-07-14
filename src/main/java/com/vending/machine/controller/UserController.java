package com.vending.machine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vending.machine.dto.UserRequest;
import com.vending.machine.dto.UserResponse;
import com.vending.machine.service.UserService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRequest request) throws JsonProcessingException {
        log.info("[UserService][register] Request: {}", mapper.writeValueAsString(request));
        String result =  userService.register(request);
        log.info("[UserService][register] Response: {}", mapper.writeValueAsString(result));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Principal principal) {
        log.info("[UserService][login] Request: {}", principal.getName());
        UserResponse userResponse =  userService.getCurrentUser(principal.getName());
        log.info("[UserService][login] Response: {}", userResponse);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);

    }

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}