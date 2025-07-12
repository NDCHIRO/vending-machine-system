package com.vending.machine.controller;

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

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @GetMapping("/my")
    public ResponseEntity<List<ProductResponse>> getMyProducts(Principal principal) {
        return new ResponseEntity<>(productService.getProductsBySeller(principal.getName()),HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<String>  createProduct(@Valid @RequestBody ProductRequest request, Principal principal) {
        return new ResponseEntity<>(productService.createProduct(request, principal.getName()),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>  updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductRequest request, Principal principal)  {
        return new ResponseEntity<>(productService.updateProduct(id, request, principal.getName()),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id, Principal principal) {
        return new ResponseEntity<>(productService.deleteProduct(id, principal.getName()), HttpStatus.OK);
    }
}
