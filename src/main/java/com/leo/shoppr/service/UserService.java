package com.leo.shoppr.service;

import com.leo.shoppr.dto.mapper.UserMapper;
import com.leo.shoppr.dto.request.CreateUserRequest;
import com.leo.shoppr.dto.response.UserResponse;
import com.leo.shoppr.exception.UserNotFoundException;
import com.leo.shoppr.entity.User;
import com.leo.shoppr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public UserResponse getUser(String id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) throw new UserNotFoundException("id", id);

        return userMapper.toDTO(user.get());
    }

    public UserResponse signUp(CreateUserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);

        return userMapper.toDTO(userRepository.save(user));
    }
}
