package com.leo.shoppr.service;

import com.leo.shoppr.exception.UserNotFoundException;
import com.leo.shoppr.entity.User;
import com.leo.shoppr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("id", id));
    }

    public User signUp(User user) {
        return userRepository.save(user);
    }
}
