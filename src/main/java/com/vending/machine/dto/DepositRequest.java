package com.vending.machine.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepositRequest {
    private Integer coin; // should be one of: 5, 10, 20, 50, 100

}
