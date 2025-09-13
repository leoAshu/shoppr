package com.leo.shoppr.config;

import com.leo.shoppr.entity.Product;
import com.leo.shoppr.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.save(new Product("p1", "Laptop", "14-inch ultrabook with 16GB RAM", 999.99, 12));
                productRepository.save(new Product("p2", "Smartphone", "6.5-inch AMOLED display, 128GB storage", 699.49, 30));
                productRepository.save(new Product("p3", "Headphones", "Noise-cancelling over-ear headphones", 149.89, 50));
                productRepository.save(new Product("p4", "Monitor", "27-inch 4K UHD monitor", 329.00, 15));
                productRepository.save(new Product("p5", "Keyboard", "Mechanical keyboard with RGB lighting", 89.99, 40));
            }
        };
    }
}
