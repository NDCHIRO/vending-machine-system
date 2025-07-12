package com.vending.machine.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_name")
    private String productName;
    private Integer cost;
    @Column(name = "amount_available")
    private Integer amountAvailable;
    @ManyToOne
    @JoinColumn(name = "seller_id")
    private User seller;
}
