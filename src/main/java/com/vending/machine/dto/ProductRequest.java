package com.vending.machine.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    @NotBlank
    private String productName;
    @Min(1)
    private Integer cost;
    private Integer amountAvailable;
}
