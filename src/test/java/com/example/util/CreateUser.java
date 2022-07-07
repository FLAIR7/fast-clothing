package com.example.util;

import com.example.domain.dto.user.UserPasswordRequest;
import com.example.domain.dto.user.UserPostRequest;
import com.example.domain.dto.user.UserResponse;
import com.example.domain.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CreateUser {

    public static User createUser(){
        return new User(
                "james@gmail.com",
                "james123"
        );
    }

    public static User createUserWithId(){
        return new User(
                UUID.fromString("c3e4a88f-c2b2-4822-87f0-06fd7cefb0cb"),
                "james@gmail.com",
                "james123"
        );
    }

    public static UserPasswordRequest createUserWithPassword(){
        return new UserPasswordRequest(
                UUID.fromString("c3e4a88f-c2b2-4822-87f0-06fd7cefb0cb"),
                "james123"
        );
    }

    public static List<User> createListUser(){
        List<User> list = new ArrayList<>();
        list.add(new User("james@gmail.com", "james123"));
        list.add(new User("bond@gmail.com", "bond123"));
        return list;
    }

    public static UserPostRequest createUserPostRequest(){
        return new UserPostRequest("james@gmail.com", "james123");
    }

    public static UserResponse createUserResponse(){
        return new UserResponse(
                UUID.fromString("c3e4a88f-c2b2-4822-87f0-06fd7cefb0cb"),
                "james@gmail.com"
        );
    }
}
