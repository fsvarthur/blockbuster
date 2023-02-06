package com.example.blockbuster.controller;



import com.example.blockbuster.controller.dto.UserDTO;
import com.example.blockbuster.model.UserAcc;
import com.example.blockbuster.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.ResponseEntity.ok;


@RestController("/api/login")
public class LoginController {
    private final static Logger LOG = LoggerFactory.getLogger(LoginController.class);

    private UserService userService;

    @PostMapping
    public ResponseEntity<Optional<UserAcc>> createUser(@RequestBody UserDTO user){
        return ResponseEntity
                .ok(userService.createUser(user));
    }

}
