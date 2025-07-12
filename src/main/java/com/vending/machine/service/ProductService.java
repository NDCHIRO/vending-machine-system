package com.vending.machine.service;

import com.vending.machine.dto.ProductRequest;
import com.vending.machine.dto.ProductResponse;
import com.vending.machine.exception.GlobalException;

import java.util.List;

public interface ProductService {
    String createProduct(ProductRequest request, String sellerUsername) throws GlobalException;
    String updateProduct(Integer productId, ProductRequest request, String sellerUsername) throws GlobalException;
    String deleteProduct(Integer productId, String sellerUsername) throws GlobalException;
    List<ProductResponse> getAllProducts();
    List<ProductResponse> getProductsBySeller(String sellerUsername);
}
