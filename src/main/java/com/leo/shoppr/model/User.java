package com.leo.shoppr.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "Username cannot be blank")
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Email
    @NotBlank(message = "Email address cannot be blank")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRole role = UserRole.USER;

    protected User() {
    }

    public User(String username, String email, String password, UserRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String username;
        private String email;
        private String password;
        private UserRole role = UserRole.USER;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(UserRole role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(username, email, password, role);
        }
    }
}
