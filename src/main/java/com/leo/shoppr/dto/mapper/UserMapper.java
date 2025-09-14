package com.leo.shoppr.dto.mapper;

import com.leo.shoppr.dto.request.CreateUserRequest;
import com.leo.shoppr.dto.response.UserResponse;
import com.leo.shoppr.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity (CreateUserRequest dto) {
        return User.builder()
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .build();
    }

    public UserResponse toDTO(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return response;
    }
}
