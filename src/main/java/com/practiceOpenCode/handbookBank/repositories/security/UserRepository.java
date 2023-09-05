package com.practiceOpenCode.handbookBank.repositories.security;

import com.practiceOpenCode.handbookBank.models.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
