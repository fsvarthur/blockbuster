package com.example.blockbuster.repository;

import com.example.blockbuster.model.UserAcc;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMgmentRepository extends JpaRepository<UserAcc, Long> {
}
