package com.example.controller;


import com.example.domain.dto.user.UserPostRequest;
import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import com.example.domain.services.UserService;
import com.example.util.CreateUser;
import com.example.util.UserMapperTest;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    UserController controller;

    @Mock
    UserService service;
    @Mock
    UserRepository repository;

    @Mock
    UserMapperTest mapper;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        Mockito.when(repository.findAll()).thenReturn(CreateUser.createListUser());
        Mockito.when(repository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(CreateUser.createUserWithId()));
        Mockito.when(repository.save(ArgumentMatchers.any(User.class))).thenReturn(CreateUser.createUserWithId());
        Mockito.when(service.saveUser(ArgumentMatchers.any(User.class))).thenReturn(CreateUser.createUserWithId());
        Mockito.when(mapper.toUser(CreateUser.createUserPostRequest())).thenReturn(CreateUser.createUserWithId());
        Mockito.when(mapper.toUserResponse(CreateUser.createUserWithId())).thenReturn(CreateUser.createUserResponse());
    }

    @Test
    void getEndpoint_GetEndpoint_WhenSuccessful() throws Exception{
        mockMvc.perform(get("/api/v1/users"))
                .andExpect(status().isOk());
    }

    @Test
    void saveUser_ReturnsUser_WhenSuccessful() throws Exception{
        UserPostRequest user = CreateUser.createUserPostRequest();
        Gson gson = new Gson();
        String toJson = gson.toJson(user);

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson)
                        .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andReturn();

    }

}