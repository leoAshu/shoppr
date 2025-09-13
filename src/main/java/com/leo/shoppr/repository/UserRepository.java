package com.leo.shoppr.repository;

import com.leo.shoppr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
