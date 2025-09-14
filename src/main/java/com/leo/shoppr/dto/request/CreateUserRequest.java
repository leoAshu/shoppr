package com.leo.shoppr.dto.request;

import com.leo.shoppr.entity.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateUserRequest {

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Email
    @NotBlank(message = "Email address cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;

    public CreateUserRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
