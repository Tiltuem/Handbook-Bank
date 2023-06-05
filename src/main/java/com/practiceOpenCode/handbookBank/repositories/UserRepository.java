package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
