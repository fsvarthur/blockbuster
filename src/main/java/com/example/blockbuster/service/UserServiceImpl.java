package com.example.blockbuster.service;

import com.example.blockbuster.controller.dto.UserDTO;
import com.example.blockbuster.model.UserAcc;
import com.example.blockbuster.repository.UserRepository;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserAcc> createUser(UserDTO user) {
        return Optional.of(userRepository.save(toEntity(user)));
    }

    UserAcc toEntity(UserDTO user){
        UserAcc acc = new UserAcc();
        return acc;
    }
}
