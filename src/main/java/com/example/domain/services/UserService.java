package com.example.domain.services;

import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User saveUser(User user){
        Optional<User> optUser = repository.findByEmail(user.getEmail());
        boolean exists = false;

        if(optUser.isPresent()){
            if(!optUser.get().userId.equals(user.getUserId())){
                exists = true;
            }
        }

        if(exists){
            throw new RuntimeException("Email cadastrado já existe");
        }

        return repository.save(user);
    }

    public List<User> listAll(){
        return repository.findAll();
    }

    public Optional<User> listById(UUID id){
        return repository.findById(id);
    }
}
