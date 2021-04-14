package com.rayllanderson.gerenciadordecompras.integration;

import com.rayllanderson.gerenciadordecompras.GerenciadorDeComprasApplication;
import com.rayllanderson.gerenciadordecompras.api.security.jwt.JwtUtil;
import com.rayllanderson.gerenciadordecompras.domain.services.UserService;
import com.rayllanderson.gerenciadordecompras.utils.user.UserCreator;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Log4j2
@SpringBootTest(classes = GerenciadorDeComprasApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseApiTest {

    @Autowired
    protected TestRestTemplate rest;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("userDetailsService")
    protected UserDetailsService userDetailsService;

    private String jwtToken = "";

    HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwtToken);
        return headers;
    }

    @BeforeEach
    public void setupTest() {
        userService.save(UserCreator.createUserPostRequestBody()); //registrando o usuário
        userService.save(UserCreator.createAnotherUserPostRequestBody()); //registrando usuário 2
        UserDetails user = userDetailsService.loadUserByUsername(UserCreator.createUserResponseBody().getUsername());
        jwtToken = JwtUtil.createToken(user);
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