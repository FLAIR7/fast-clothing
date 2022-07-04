package com.example.domain.mapper;

import com.example.domain.dto.user.UserPostRequest;
import com.example.domain.dto.user.UserResponse;
import com.example.domain.model.User;
import org.modelmapper.ModelMapper;

public class UserMapper {

    public static User toUser(UserPostRequest request){
        return new ModelMapper().map(request, User.class);
    }

    public static UserResponse toUserResponse(User user){
        return new ModelMapper().map(user, UserResponse.class);
    }
}
