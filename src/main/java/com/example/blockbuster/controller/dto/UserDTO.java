package com.example.blockbuster.controller.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {

    private String username;
    private Long Id;
    private String password;
   // private List<GrantedAuthority> authorities = new ArrayList<>();
}
