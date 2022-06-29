package com.example.domain.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY, generator = "uuid2"
    )
    @GenericGenerator(
            name = "uuid2", strategy = "uuid2"
    )
    @Type(
            type = "uuid-char"
    )
    @ColumnDefault("random_uuid()")
    public UUID userId;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    @Size(min = 8, max = 25)
    private String password;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(){

    }

    public UUID getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
