package com.vending.machine.repository;

import com.vending.machine.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    Optional<Product> findByProductName(String productName);
    List<Product> findBySellerUsername(String username);
    Optional<Product> findByProductIdAndSeller_Username(Integer productId, String username);
}
