package com.leo.shoppr.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    private String id;

    @NotBlank(message = "Product name cannot be blank")
    @Size(max = 100, message = "Name must not exceed 100 characters")
    @Column(name="name", nullable = false, length = 100)
    private String name;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    @Column(name = "description", length = 500)
    private String description;

    @Positive(message = "Price must be greater than 0")
    @Digits(integer = 10, fraction = 2, message = "Price must be a valid monetary amount")
    @Column(name = "price", precision = 12, scale = 2, nullable = false)
    private BigDecimal price;

    @Min(value = 0, message = "Stock cannot be negative")
    @Column(name="stock", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int stock = 0;

    public Product() {

    }

    public Product(String id, String name, String description, double price, int stock) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = BigDecimal.valueOf(price);
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
