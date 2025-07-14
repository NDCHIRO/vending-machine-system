package com.vending.machine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vending.machine.dto.ProductRequest;
import com.vending.machine.dto.ProductResponse;
import com.vending.machine.exception.GlobalException;
import com.vending.machine.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    ObjectMapper mapper = new ObjectMapper();

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        log.info("[ProductService][get all products]");
        ResponseEntity<List<ProductResponse>> productList =  new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
        log.info("[ProductService][get all products]");
        return productList;
    }

    @GetMapping("/my")
    public ResponseEntity<List<ProductResponse>> getMyProducts(Principal principal) throws GlobalException, JsonProcessingException {
        log.info("[ProductService][get Current User products] Request: {}", mapper.writeValueAsString(principal.getName()));
        ResponseEntity<List<ProductResponse>> productList =  new ResponseEntity<>(productService.getProductsBySeller(principal.getName()),HttpStatus.OK);
        log.info("[ProductService][get Current User products] Response: {}", mapper.writeValueAsString(productList));
        return productList;
    }


    @PostMapping("/create")
    public ResponseEntity<String>  createProduct(@Valid @RequestBody ProductRequest request, Principal principal) throws JsonProcessingException {
        log.info("[ProductService][create product] Request: {}", mapper.writeValueAsString(request));
        ResponseEntity<String> response = new ResponseEntity<>(productService.createProduct(request, principal.getName()),HttpStatus.OK);
        log.info("[ProductService][create product] Response: {}", mapper.writeValueAsString(response));
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>  updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductRequest request, Principal principal) throws JsonProcessingException {
        log.info("[ProductService][update product] Request: {}", mapper.writeValueAsString(request));
        ResponseEntity<String> response = new ResponseEntity<>(productService.updateProduct(id, request, principal.getName()),HttpStatus.OK);
        log.info("[ProductService][update product] Response: {}", mapper.writeValueAsString(response));
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id, Principal principal) throws JsonProcessingException {
        log.info("[ProductService][delete product] Request: {}", mapper.writeValueAsString(principal.getName()));
        ResponseEntity<String> response = new ResponseEntity<>(productService.deleteProduct(id, principal.getName()), HttpStatus.OK);
        log.info("[ProductService][delete product] Response: {}", mapper.writeValueAsString(response));
        return response;
    }
}
