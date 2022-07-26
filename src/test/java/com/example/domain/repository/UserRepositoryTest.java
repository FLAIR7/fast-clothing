package com.example.domain.repository;

import com.example.domain.model.User;
import com.example.util.CreateUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@DisplayName("User Repository Tests")
class UserRepositoryTest {

    private final UserRepository repository;

    @Autowired
    public UserRepositoryTest(UserRepository repository){
        this.repository = repository;
    }

    @Test
    void save_PersistUser_WhenSuccessful(){
        User user = CreateUser.createUser();
        User userSaved = repository.save(user);

        Assertions.assertThat(userSaved).isNotNull();
        Assertions.assertThat(userSaved.getUserId()).isNotNull();
        Assertions.assertThat(userSaved.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    void findById_ReturnsUser_WhenSuccessful(){
        User user = CreateUser.createUser();
        User userSaved = repository.save(user);

        Optional<User> optUser = repository.findById(userSaved.getUserId());

        Assertions.assertThat(optUser).isPresent();
        Assertions.assertThat(optUser.get().getUserId()).isNotNull();
    }

    @Test
    void save_UpdateUser_WhenSuccessful(){
        User user = CreateUser.createUser();
        User userSaved = repository.save(user);
        User userUpdated;

        userSaved.setEmail("juan@gmail.com");

        userUpdated = repository.save(userSaved);
        Assertions.assertThat(userUpdated).isNotNull();
        Assertions.assertThat(userUpdated).isEqualTo(userSaved);

    }

    @Test
    void deleteById_DeleteUser_WhenSuccessful(){
        User user = CreateUser.createUser();
        User userSaved = repository.save(user);

        repository.deleteById(userSaved.getUserId());

        Optional<User> optUser = repository.findById(userSaved.getUserId());
        Assertions.assertThat(optUser).isNotPresent();
        Assertions.assertThat(optUser).isEmpty();
    }
}