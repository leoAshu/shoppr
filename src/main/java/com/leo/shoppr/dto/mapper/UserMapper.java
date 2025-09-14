package com.leo.shoppr.dto.mapper;

import com.leo.shoppr.dto.response.UserResponse;
import com.leo.shoppr.entity.User;

public class UserMapper {

    public UserResponse toDTO(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());

        return response;
    }
}
