package com.example.blockbuster.Controller;


import com.example.blockbuster.Controller.dto.UserDto;
import com.example.blockbuster.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public Iterable<UserDto> getUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable("id") String id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto postUser(@Valid @RequestBody UserDto userDto)
            throws NoSuchAlgorithmException {
        return userService.createUser(userDto, userDto.getPassword());
    }

    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") String id, @Valid @RequestBody UserDto userDto)
            throws NoSuchAlgorithmException {
        userService.updateUser(id, userDto, userDto.getPassword());
    }

}
