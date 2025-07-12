package com.vending.machine.dto;

import com.vending.machine.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private Role role;
}