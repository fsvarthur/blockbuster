package com.example.blockbuster.security;

import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableMethodSecurity
public class SecurityConfig {
/*
    @Bean
    UserDetailsService userService(UserRepository repo){
        return username -> repo.findByUsername(username).asUser();
    }

    @Bean
    SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests()
                .antMatchers("/login").permitAll()
                .antMatchers().authenticated()
                .antMatchers(HttpMethod.GET, "/api/**").authenticated()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/delete/**", "/new-video").authenticated()
                .anyRequest().denyAll()
                .and().formLogin()
                .and().httpBasic();
        return http.build();
    }

*/

}
