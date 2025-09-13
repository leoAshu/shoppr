package com.leo.shoppr.config;

import com.leo.shoppr.entity.*;
import com.leo.shoppr.repository.OrderRepository;
import com.leo.shoppr.repository.ProductRepository;
import com.leo.shoppr.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataSeeder {

    @Bean
    @org.springframework.core.annotation.Order(1)
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

    @Bean
    @org.springframework.core.annotation.Order(2)
    CommandLineRunner seedUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(User.builder().email("ash@mail.com").username("ash").password("pass").role(UserRole.USER).build());
                userRepository.save(User.builder().email("leo@mail.com").username("leo").password("pass").role(UserRole.USER).build());
                userRepository.save(User.builder().email("ojha@mail.com").username("ojha").password("pass").role(UserRole.ADMIN).build());
            }
        };
    }

    @Bean
    @org.springframework.core.annotation.Order(3)
    CommandLineRunner seedOrders(OrderRepository orderRepository,
                                 UserRepository userRepository,
                                 ProductRepository productRepository) {
        return args -> {
            if (orderRepository.count() == 0) {
                User user = userRepository.findByUsername("ash")
                        .orElseThrow(() -> new RuntimeException("User not found"));
                Product laptop = productRepository.findById("p1")
                        .orElseThrow(() -> new RuntimeException("Product not found"));
                Product phone = productRepository.findById("p2")
                        .orElseThrow(() -> new RuntimeException("Product not found"));

                Order order = Order.builder()
                        .user(user)
                        .total(BigDecimal.valueOf(1699.48)) // laptop + phone
                        .status(OrderStatus.PENDING)
                        .build();

                OrderItem item1 = OrderItem.builder()
                        .order(order)   // ✅ set back-reference
                        .quantity(1)
                        .unitPrice(laptop.getPrice())
                        .build();

                OrderItem item2 = OrderItem.builder()
                        .order(order)   // ✅ set back-reference
                        .quantity(1)
                        .unitPrice(phone.getPrice())
                        .build();

                order.getItems().add(item1);
                order.getItems().add(item2);

                orderRepository.save(order);
            }
        };
    }
}
