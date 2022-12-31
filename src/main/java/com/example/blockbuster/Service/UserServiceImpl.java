package com.example.blockbuster.Service;

import com.example.blockbuster.Controller.dto.UserDto;
import com.example.blockbuster.Exception.BadRequestException;
import com.example.blockbuster.Exception.NotFoundException;
import com.example.blockbuster.Model.User;
import com.example.blockbuster.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public User searchByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<UserDto> findAllUsers() {
        List<User> userList = new ArrayList<>(userRepository.findAll());
        return userList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public UserDto findUserById(String id) {
        User user = userRepository.findById(Long.valueOf(id)).orElseThrow(
                () -> new NotFoundException("User by id" + id + " was not found")
        );
        return convertToDto(user);
    }

    public UserDto createUser(UserDto userDto, String password)
            throws NoSuchAlgorithmException {

        User user = convertToEntity(userDto);
        if (password.isBlank()) throw new IllegalArgumentException(
                "Password is required"
        );

        var existsEmail = userRepository.selectExistsEmail(user.getEmail());
        if (existsEmail) throw new BadRequestException(
                "Email " + user.getEmail() + "taken"
        );

        byte[] salt = createSalt();
        byte[] hashedPassword = createPasswordHash(password, salt);

        user.setStoredSalt(salt);
        user.setStoredHash(hashedPassword);

        userRepository.save(user);

        return convertToDto(user);
    }

    public void updateUser(String id, UserDto userDto, String password)
            throws NoSuchAlgorithmException {
        var user = findOrThrow(id);
        var userParam = convertToEntity(userDto);

        user.setEmail(userParam.getEmail());
        user.setMobileNumber(userParam.getMobileNumber());

        if (!password.isBlank()) {
            byte[] salt = createSalt();
            byte[] hashedPassword = createPasswordHash(password, salt);

            user.setStoredSalt(salt);
            user.setStoredHash(hashedPassword);
        }
        userRepository.save(user);
    }

    public void deleteUser(String id) {
        findOrThrow(id);
        userRepository.deleteById(Long.valueOf(id));
    }


    private byte[] createSalt() {
        var random = new SecureRandom();
        var salt = new byte[128];
        random.nextBytes(salt);
        return salt;
    }

    private byte[] createPasswordHash(String password, byte[] salt) throws NoSuchAlgorithmException {
        var md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        return md.digest(
                password.getBytes(StandardCharsets.UTF_8)
        );
    }

    private User findOrThrow(String id) {
        return userRepository
                .findById(Long.valueOf(id))
                .orElseThrow(
                        () -> new NotFoundException("User by id" + id + " was not found.")
                );
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
