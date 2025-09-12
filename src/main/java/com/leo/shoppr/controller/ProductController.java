package com.leo.shoppr.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @GetMapping("/products")
    public ResponseEntity<String> getAllProducts() {
        return ResponseEntity.ok("All Products");
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<String> getProductById(@PathVariable String id) {
        return ResponseEntity.ok("Product with id: ".concat(id));
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct() {
        return ResponseEntity.ok("Product created!");
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable String id) {
        return ResponseEntity.ok("Product updated with id: ".concat(id));
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable String id) {
        return ResponseEntity.ok("Product deleted with id: ".concat(id));
    }
}
