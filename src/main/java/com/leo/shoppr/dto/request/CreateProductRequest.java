package com.leo.shoppr.dto.request;

import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

public class CreateProductRequest {

    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @Positive(message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must be a valid monetary amount")
    private BigDecimal price;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stock = 0;

    public CreateProductRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
