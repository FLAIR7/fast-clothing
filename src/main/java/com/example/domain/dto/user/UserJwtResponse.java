package com.example.domain.dto.user;

import com.example.domain.model.User;
import org.modelmapper.ModelMapper;

public class UserJwtResponse {
    private String email;
    private String token;

    public UserJwtResponse(String email,
                           String token) {
        this.email = email;
        this.token = token;
    }

    public UserJwtResponse(){

    }

    public static UserJwtResponse create(User user, String token) {
        UserJwtResponse userDetails = create(user);
        userDetails.setToken(token);
        return userDetails;
    }

    public static UserJwtResponse create(User user){
        return new ModelMapper().map(user, UserJwtResponse.class);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
