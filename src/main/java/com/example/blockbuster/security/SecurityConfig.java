package com.example.blockbuster.security;

import com.example.blockbuster.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    UserDetailsService userService(UserRepository repo){
        return username -> repo.findByUsername(username).asUser();
    }

    @Bean
    SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                //.requestMatchers("/login").permitAll()
                //.requestMatchers().authenticated()
               // .requestMatchers(HttpMethod.GET, "/api/**").authenticated()
               // .requestMatchers("/admin").hasRole("ADMIN")
               //.requestMatchers(HttpMethod.POST,"/delete/**", "/new-video").authenticated()
                .anyRequest().denyAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }



}
