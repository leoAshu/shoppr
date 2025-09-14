package com.leo.shoppr.controller;

import com.leo.shoppr.entity.User;
import com.leo.shoppr.dto.common.CustomResponse;
import com.leo.shoppr.dto.common.ResponseStatus;
import com.leo.shoppr.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/{id}")
    public ResponseEntity<CustomResponse<User>> getUser(@PathVariable String id) {
        logger.debug("/user/{id}");
        logger.debug("param: {}", id);

        CustomResponse<User> response = CustomResponse.<User>builder()
                .status(ResponseStatus.SUCCESS)
                .data(userService.getUser(id))
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<CustomResponse<User>> signUp(@RequestBody User user) {
        logger.debug("/signup");
        logger.debug("param: {}", user);

        CustomResponse<User> response = CustomResponse.<User>builder()
                .status(ResponseStatus.SUCCESS)
                .message("Signup successful")
                .data(userService.signUp(user))
                .build();

        return ResponseEntity.ok(response);
    }
}
