package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.Swbics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwbicsRepository extends JpaRepository<Swbics, Long> {
}
