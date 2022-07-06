package com.example.domain.services;

import com.example.domain.dto.user.UserPasswordRequest;
import com.example.domain.exceptions.NotFoundException;
import com.example.domain.mapper.UserMapper;
import com.example.domain.model.User;
import com.example.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

@Service
@Transactional
public class UserService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optUser = repository.findByEmail(email);
        if(optUser.isEmpty()){
            throw new UsernameNotFoundException("User does not exist");
        }
        User user = optUser.get();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    @Transactional
    public void updatePassword(UserPasswordRequest user){
        if(user.getPassword() == null || (user.getPassword().isEmpty() || user.getPassword().trim().isEmpty())) {
            throw new IllegalArgumentException("Your password is null");
        }
        User userExpected = this.findById(user.getUserId()).orElse(null);
        userExpected.setPassword(passwordEncoder.encode(userExpected.getPassword()));
        UserMapper.toUserResponse(this.repository.save(userExpected));
    }
}
