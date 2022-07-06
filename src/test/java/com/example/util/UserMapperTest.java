package com.example.util;

import com.example.domain.dto.user.UserPostRequest;
import com.example.domain.dto.user.UserResponse;
import com.example.domain.model.User;
import org.modelmapper.ModelMapper;

public class UserMapperTest {

    public User toUser(UserPostRequest request){
        return new ModelMapper().map(request, User.class);
    }

    public UserResponse toUserResponse(User request){
        return new ModelMapper().map(request, UserResponse.class);
    }
}
