package com.example.blockbuster.controller;


import com.example.blockbuster.controller.dto.UserDTO;
import com.example.blockbuster.model.UserAcc;
import com.example.blockbuster.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController("/api/login")
public class LoginController {
    private final static Logger LOG = LoggerFactory.getLogger(LoginController.class);

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Optional<UserAcc>> createUser(@RequestBody UserDTO user){
        return ResponseEntity
                .ok(userService.createUser(user));
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
