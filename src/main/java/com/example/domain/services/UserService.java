package com.example.domain.services;

import com.example.domain.dto.user.UserPasswordRequest;
import com.example.domain.dto.user.UserPutRequest;
import com.example.domain.exceptions.NotFoundException;
import com.example.domain.mapper.UserMapper;
import com.example.domain.model.Role;
import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository repository, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user){
        Optional<User> optUser = repository.findByEmail(user.getEmail());
        boolean exists = false;

        if(optUser.isPresent()){
            if(!optUser.get().userId.equals(user.getUserId())){
                exists = true;
            }
        }

        if(exists){
            throw new RuntimeException("Email already in use");
        }
        
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public Optional<User> findById(UUID id){
        return repository.findById(id);
    }

    public void deleteById(UUID id) {
        Optional<User> optUser = repository.findById(id);

        if(optUser.isEmpty()){
            throw new NotFoundException("User not found");
        }

        repository.deleteById(id);
    }

    @Transactional
    public void update(UserPutRequest user){
        User userSaved = this.findById(user.getUserId()).orElse(null);
        boolean notSameEmail = !user.getEmail().equals(userSaved.getEmail());
        if(notSameEmail){
            if(user.getEmail() != null && !user.getEmail().isEmpty() && !user.getEmail().trim().isEmpty()){
                boolean emailExists = repository.existsByEmail(user.getEmail());
                if(emailExists){
                    throw new IllegalArgumentException("Email already in use, try another");
                }
                userSaved.setEmail(user.getEmail());
                UserMapper.toUserResponse(this.repository.save(userSaved));
            }
        }
    }

    @Transactional
    public void updatePassword(UserPasswordRequest user){
        if(user.getPassword() == null || (user.getPassword().isEmpty() || user.getPassword().trim().isEmpty())) {
            throw new IllegalArgumentException("Your password is null");
        }
        User userExpected = this.findById(user.getUserId()).orElse(null);
        userExpected.setPassword(passwordEncoder.encode(userExpected.getPassword()));
        repository.save(userExpected);
    }
}
