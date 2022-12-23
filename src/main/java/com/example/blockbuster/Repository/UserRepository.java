package com.example.blockbuster.Repository;

import com.example.blockbuster.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(
            "" + "SELECT CASE WHEN COUNT(u) > 0 THEN " +
                    "TRUE ELSE FALSE END " + "FROM User u " + "WHERE u.email = ?1"
    )
    Boolean selectExistsEmail(String email);

    User findByEmail(String email);
}
