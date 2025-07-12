package com.vending.machine.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
    private Integer id;
    private String productName;
    private Integer cost;
    private Integer amountAvailable;
    private String sellerUsername;
}
