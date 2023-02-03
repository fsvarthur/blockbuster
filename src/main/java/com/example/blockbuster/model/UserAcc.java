package com.example.blockbuster.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
public class UserAcc{

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<GrantedAuthority> authorityList = new ArrayList<>();

    public UserAcc() {
    }

    public UserAcc(String username, String password, String... authorityList) {
        this.username = username;
        this.password = password;
        this.authorityList = Arrays.stream(authorityList)
                .map(SimpleGrantedAuthority::new)
                .map(GrantedAuthority.class::cast)
                .toList();
    }

    public UserDetails asUser(){
        return User.withDefaultPasswordEncoder()
                .username(getUsername())
                .password(getPassword())
                .authorities(getAuthorityList())
                .build();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<GrantedAuthority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<GrantedAuthority> authorityList) {
        this.authorityList = authorityList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAcc userAcc = (UserAcc) o;
        return Objects.equals(id, userAcc.id) && Objects.equals(username, userAcc.username) && Objects.equals(password, userAcc.password) && Objects.equals(authorityList, userAcc.authorityList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, authorityList);
    }

    @Override
    public String toString() {
        return "UserAcc{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorityList=" + authorityList +
                '}';
    }
}
