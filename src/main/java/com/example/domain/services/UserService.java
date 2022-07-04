package com.example.domain.services;

import com.example.Exceptions.NotFoundException;
import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
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
            throw new RuntimeException("Email cadastrado já existe");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return repository.save(user);
    }

    public List<User> listAll(){
        return repository.findAll();
    }

    public Optional<User> listById(UUID id){
        return repository.findById(id);
    }

    public void delete(UUID id) {
        Optional<User> optUser = repository.findById(id);

        if(optUser.isEmpty()){
            throw new NotFoundException("User not found");
        }

        repository.deleteById(id);
    }
}
