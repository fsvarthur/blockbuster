package com.example.blockbuster.repository;

import com.example.blockbuster.model.UserAcc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserAcc, Long> {
    UserAcc findByUsername(String username);
}
