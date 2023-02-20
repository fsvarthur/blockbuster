package com.example.blockbuster.service;

import com.example.blockbuster.controller.dto.UserDTO;
import com.example.blockbuster.model.UserAcc;

import java.util.Optional;

public interface UserService {

    Optional<UserAcc> createUser(UserDTO user);
}
