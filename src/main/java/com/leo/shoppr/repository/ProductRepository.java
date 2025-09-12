package com.leo.shoppr.repository;

import com.leo.shoppr.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
