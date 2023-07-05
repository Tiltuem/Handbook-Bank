package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.directories.RestrictionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestrictionCodeRepository extends JpaRepository<RestrictionCode, Long> {
}