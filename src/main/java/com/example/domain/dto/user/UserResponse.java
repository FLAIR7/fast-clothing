package com.example.domain.dto.user;

import java.util.Objects;
import java.util.UUID;

public class UserResponse {

    private UUID userId;
    private String email;

    public UserResponse(){

    }

    public UserResponse(UUID userId,
                        String email) {
        this.userId = userId;
        this.email = email;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserResponse that = (UserResponse) o;
        return userId.equals(that.userId) && email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, email);
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                '}';
    }
}
