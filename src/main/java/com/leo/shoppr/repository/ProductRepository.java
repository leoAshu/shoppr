package com.leo.shoppr.repository;

import com.leo.shoppr.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {

    public Optional<Product> findByName(String name);
}
