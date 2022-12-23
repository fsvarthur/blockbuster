package com.example.blockbuster.Service;

import com.example.blockbuster.Controller.dto.UserDto;
import com.example.blockbuster.Model.User;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {

    User searchByEmail(String email);

    List<UserDto> findAllUsers();

    UserDto findUserById(String id);

    UserDto createUser(UserDto userDto, String password)
            throws NoSuchAlgorithmException;

    void updateUser(String id, UserDto userDto, String password)
            throws NoSuchAlgorithmException;

    void deleteUser(String id);
}
