package com.leo.shoppr.repository;

import com.leo.shoppr.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
