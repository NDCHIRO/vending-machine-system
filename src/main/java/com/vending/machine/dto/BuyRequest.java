package com.vending.machine.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BuyRequest {
    private Integer productId;
    private Integer quantity;
}
