package com.vending.machine.service;

import com.vending.machine.dto.UserRequest;
import com.vending.machine.dto.UserResponse;
import com.vending.machine.model.User;

import java.util.Optional;

public interface UserService {
    String register(UserRequest request);
    UserResponse getCurrentUser(String username);
}
