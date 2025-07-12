package com.vending.machine.service;

import com.vending.machine.dto.ProductRequest;
import com.vending.machine.dto.ProductResponse;
import com.vending.machine.exception.GlobalException;
import com.vending.machine.model.Product;
import com.vending.machine.model.User;
import com.vending.machine.repository.ProductRepository;
import com.vending.machine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public String createProduct(ProductRequest request, String sellerUsername) throws GlobalException {
        User seller = userRepository.findByUsername(sellerUsername)
                .orElseThrow(() -> new GlobalException("Seller not found"));

        if(checkProductExists(request.getProductName()))
            throw new GlobalException("Product already exists");

        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setAmountAvailable(request.getAmountAvailable());
        product.setCost(request.getCost());
        product.setSeller(seller);

        productRepository.save(product);
        return "Product created successfully.";
    }

    private Boolean checkProductExists(String productName)
    {
        Optional<Product> product = productRepository.findByProductName(productName);
        return product.isPresent();
    }


    @Override
    public String updateProduct(Integer productId, ProductRequest request, String sellerUsername) throws GlobalException {
        Product product = productRepository.findByProductIdAndSeller_Username(productId, sellerUsername)
                .orElseThrow(() -> new GlobalException("Product not found or not owned by seller"));

        product.setProductName(request.getProductName());
        product.setAmountAvailable(request.getAmountAvailable());
        product.setCost(request.getCost());

        productRepository.save(product);
        return "Product updated successfully.";
    }

    @Override
    public String deleteProduct(Integer productId, String sellerUsername) throws GlobalException {
        Product product = productRepository.findByProductIdAndSeller_Username(productId, sellerUsername)
                .orElseThrow(() -> new GlobalException("Product not found or not owned by seller"));

        productRepository.delete(product);
        return "Product deleted successfully.";
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductsBySeller(String sellerUsername) {
        return productRepository.findBySellerUsername(sellerUsername)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getProductId());
        response.setProductName(product.getProductName());
        response.setCost(product.getCost());
        response.setAmountAvailable(product.getAmountAvailable());
        response.setSellerUsername(product.getSeller().getUsername());
        return response;

    }
}
