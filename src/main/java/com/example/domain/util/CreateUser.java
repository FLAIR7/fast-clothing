package com.example.domain.util;

import com.example.domain.dto.user.UserPasswordRequest;
import com.example.domain.model.User;

import java.util.UUID;

public class CreateUser {

    public static User createUser(){
        return new User("james@gmail.com", "james123");
    }

    public static User createUserWithId(){
        return new User(UUID.fromString("c3e4a88f-c2b2-4822-87f0-06fd7cefb0cb"), "james@gmail.com", "james123");
    }

    public static UserPasswordRequest createUserWithPassword(){
        return new UserPasswordRequest(UUID.fromString("c3e4a88f-c2b2-4822-87f0-06fd7cefb0cb"), "james123");
    }
}
