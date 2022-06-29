package com.example.controller;

import com.example.domain.model.User;
import com.example.domain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        User salvar = service.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvar);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable UUID id){
        Optional<User> user = service.listById(id);

        if(user.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

}
