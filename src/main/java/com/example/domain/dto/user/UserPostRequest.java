package com.example.domain.dto.user;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class UserPostRequest {

    private String email;
    @NotNull
    private String password;

    public UserPostRequest(String email, String password) {
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "UserPostRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
