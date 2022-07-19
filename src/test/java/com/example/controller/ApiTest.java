package com.example.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.DemoApplication;
import com.example.domain.mapper.UserMapper;
import com.example.domain.services.UserService;
import com.example.util.CreateUser;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Date;
import java.util.stream.Collectors;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ApiTest {

    @Autowired
    protected TestRestTemplate rest;

    @Autowired
    private UserService service;

    @Autowired
    @Qualifier("userDetailsService")
    protected UserDetailsService userDetailsService;

    protected String jwtToken = "";

    HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);
        return headers;
    }

    @BeforeEach
    void setupTest(){
        service.saveUser(UserMapper.toUser(CreateUser.createUserPostRequest()));
        UserDetails user = userDetailsService.loadUserByUsername(CreateUser.createUserResponse().getEmail());
        Algorithm algorithm = Algorithm.HMAC256("batman");

        String acess_token = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    }

    <T> ResponseEntity<T> post(String url, Object body, Class<T> responseType) {
        HttpHeaders headers = getHeaders();
        return rest.exchange(url, HttpMethod.POST, new HttpEntity<>(body, headers), responseType);
    }

    <T> ResponseEntity<T> get(String url, Class<T> responseType) {
        HttpHeaders headers = getHeaders();
        return rest.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), responseType);
    }

    <T> ResponseEntity<T> delete(String url, Class<T> responseType) {
        HttpHeaders headers = getHeaders();
        return rest.exchange(url, HttpMethod.DELETE, new HttpEntity<>(headers), responseType);
    }

    <T> ResponseEntity<T> put(String url, Object body, Class<T> responseType) {
        HttpHeaders headers = getHeaders();
        return rest.exchange(url, HttpMethod.PUT, new HttpEntity<>(body, headers), responseType);
    }

}
