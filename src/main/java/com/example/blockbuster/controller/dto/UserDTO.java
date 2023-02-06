package com.example.blockbuster.controller.dto;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO implements Serializable {

    private String username;
    private Long Id;
    private String password;
    private List<GrantedAuthority> authorities = new ArrayList<>();
}
