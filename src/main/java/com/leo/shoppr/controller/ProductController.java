package com.leo.shoppr.controller;

import com.leo.shoppr.model.Product;
import com.leo.shoppr.response.CustomResponse;
import com.leo.shoppr.response.ResponseStatus;
import com.leo.shoppr.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Product>>> getAllProducts() {
        CustomResponse<List<Product>> response = new CustomResponse<>();

        response.setStatus(ResponseStatus.SUCCESS);
        response.setData(productService.getAllProducts());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Product>> getProductById(@PathVariable String id) {
        CustomResponse<Product> response = new CustomResponse<>();

        response.setStatus(ResponseStatus.SUCCESS);
        response.setData(productService.getProductById(id));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Product>> createProduct(@Valid @RequestBody Product product) {
        CustomResponse<Product> response = new CustomResponse<>();

        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("Product created successfully.");
        response.setData(productService.createProduct(product));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Product>> updateProduct(@PathVariable String id, @Valid @RequestBody Product product) {
        CustomResponse<Product> response = new CustomResponse<>();

        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("Product updated successfully.");
        response.setData(productService.updateProduct(id, product));

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteProduct(@PathVariable String id) {
        CustomResponse<Void> response = new CustomResponse<>();

        response.setStatus(ResponseStatus.SUCCESS);
        response.setMessage("Product deleted successfully.");
        productService.deleteProduct(id);

        return ResponseEntity.ok(response);
    }
}
