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
        CustomResponse<List<Product>> response = CustomResponse.<List<Product>>builder()
                .status(ResponseStatus.SUCCESS)
                .data(productService.getAllProducts())
                .build();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Product>> getProductById(@PathVariable String id) {
        CustomResponse<Product> response = CustomResponse.<Product>builder()
                .status(ResponseStatus.SUCCESS)
                .data(productService.getProductById(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Product>> createProduct(@Valid @RequestBody Product product) {
        CustomResponse<Product> response = CustomResponse.<Product>builder()
                .status(ResponseStatus.SUCCESS)
                .message("Product created successfully")
                .data(productService.createProduct(product))
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Product>> updateProduct(@PathVariable String id, @Valid @RequestBody Product product) {
        CustomResponse<Product> response = CustomResponse.<Product>builder()
                .status(ResponseStatus.SUCCESS)
                .message("Product updated successfully")
                .data(productService.updateProduct(id, product))
                .build();

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteProduct(@PathVariable String id) {
        CustomResponse<Void> response = CustomResponse.<Void>builder()
                .status(ResponseStatus.SUCCESS)
                .message("Product deleted successfully")
                .build();

        productService.deleteProduct(id);

        return ResponseEntity.ok(response);
    }
}
