package com.example.domain.services;

import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import com.example.util.CreateUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    UserRepository repository;

    @Mock
    PasswordEncoder passwordEncoder;

    public UserServiceTest(){}

    @BeforeEach
    void setUp(){
        Mockito.when(repository.save(ArgumentMatchers.any(User.class))).thenReturn(CreateUser.createUserWithId());
        Mockito.when(repository.findById(ArgumentMatchers.any(UUID.class))).thenReturn(Optional.of(CreateUser.createUserWithId()));
        Mockito.doNothing().when(repository).deleteById(ArgumentMatchers.any(UUID.class));
        Mockito.when(passwordEncoder.encode(ArgumentMatchers.anyString())).thenReturn("$2a$10$ZLhnHxdpHETcxmtEStgpI./Ri1mksgJ9iDP36FmfMdYyVg9g0b2dq");
    }

    @Test
    void saveUser_SavesUser_WhenSuccessful(){
        User user = service.saveUser(CreateUser.createUserWithId());
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUserId()).isNotNull();
    }

    @Test
    void findById_ReturnUser_WhenSuccessful(){
        User userExpected = CreateUser.createUserWithId();
        User user = service.findById(userExpected.getUserId()).orElse(null);
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user).isEqualTo(userExpected);
    }

    @Test
    void deleteById_DeleteUser_WhenSuccessful(){
        Assertions.assertThatCode(() -> service.deleteById(UUID.fromString("c3e4a88f-c2b2-4822-87f0-06fd7cefb0cb"))).doesNotThrowAnyException();
        Mockito.verify(repository).deleteById(UUID.fromString("c3e4a88f-c2b2-4822-87f0-06fd7cefb0cb"));
    }

    @Test
    void updatePassword_UpdatePassword_WhenSuccessful(){
        Assertions.assertThatCode(() -> service.updatePassword(CreateUser.createUserWithPassword())).doesNotThrowAnyException();
    }
}