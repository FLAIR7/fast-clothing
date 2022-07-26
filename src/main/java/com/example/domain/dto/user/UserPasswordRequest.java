package com.example.domain.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class UserPasswordRequest {

    @NotNull
    private UUID userId;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 25)
    private String password;

    public UserPasswordRequest(){

    }

    public UserPasswordRequest(UUID userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserPasswordRequest{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                '}';
    }
}
