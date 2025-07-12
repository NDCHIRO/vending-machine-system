package com.vending.machine.service;

import com.vending.machine.dto.UserRequest;
import com.vending.machine.dto.UserResponse;
import com.vending.machine.model.Role;
import com.vending.machine.model.User;
import com.vending.machine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String register(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setDeposit(0);

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "Username already exists.";
        }
        userRepository.save(user);
        return "User registered successfully!";
    }

    @Override
    public UserResponse getCurrentUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setDeposit(user.getDeposit());
        response.setRole(user.getRole());  // This should not cause any loop

        return response;
    }
}