package com.vending.machine.dto;

import com.vending.machine.model.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private String username;
    private Role role;
    private Integer deposit;
}