package com.example.controller;

import com.example.domain.dto.user.UserPasswordRequest;
import com.example.domain.dto.user.UserPostRequest;
import com.example.domain.dto.user.UserPutRequest;
import com.example.domain.dto.user.UserResponse;
import com.example.domain.mapper.UserMapper;
import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import com.example.domain.services.UserService;
import com.example.exceptions.NotFoundException;
import com.example.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService service,
                          UserRepository userRepository) {
        this.service = service;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(){
        List<UserResponse> users = userRepository
                .findAll()
                .stream()
                .map(UserMapper::toUserResponse)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id){
        Optional<User> user = service.findById(id);

        if(user.isEmpty()){
            throw new NotFoundException("User not found");
        }
        UserResponse response = UserMapper.toUserResponse(user.get());

        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/info")
    public ResponseEntity<UserResponse> info(@AuthenticationPrincipal UserPrincipal user){
        System.out.println(user.getUsername());
        User userSaved = service.findByEmail(user.getUsername()).orElse(null);
        UserResponse response = UserMapper.toUserResponse(userSaved);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserPostRequest request){
        User user = UserMapper.toUser(request);
        User userSave = service.saveUser(user);
        UserResponse userResponse = UserMapper.toUserResponse(userSave);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateEmail(@AuthenticationPrincipal UserPrincipal user,
                                                      @Valid @RequestBody UserPutRequest request) {
        service.update(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/update/password")
     public ResponseEntity<Void> updatePassword (@AuthenticationPrincipal UserPrincipal user,
                                                    @Valid @RequestBody UserPasswordRequest request) {
        service.updatePassword(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
