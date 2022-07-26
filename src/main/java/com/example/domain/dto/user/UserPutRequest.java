package com.example.domain.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class UserPutRequest {

    @NotNull
    private UUID userId;

    @NotBlank(message = "Email is required")
    @Email(message = "This doesnt look like an email")
    @NotNull
    private String email;

    public UserPutRequest() {
    }

    public UserPutRequest(UUID userId, String email) {
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
        UserPutRequest that = (UserPutRequest) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "UserPutRequest{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                '}';
    }
}
