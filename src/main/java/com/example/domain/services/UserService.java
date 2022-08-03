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
    public UserService(UserRepository repository,
                       PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
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
        
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public List<User> findAll(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<User> findById(UUID id){
        return repository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> findByEmail(String email){
        return repository.findByEmail(email);
    }

    @Transactional
    public void deleteById(UUID id) {
        Optional<User> optUser = repository.findById(id);

        if(optUser.isEmpty()){
            throw new NotFoundException("User not found");
        }

        repository.deleteById(id);
    }

    @Transactional
    public void update(UserPutRequest user){
        User userSaved = repository.findById(user.getUserId()).orElse(null);
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
        User userExpected = repository.findById(user.getUserId()).orElse(null);
        System.out.println(userExpected.getPassword());
        userExpected.setPassword(passwordEncoder.encode(userExpected.getPassword()));
        UserMapper.toUserResponse(this.repository.save(userExpected));
    }
}
