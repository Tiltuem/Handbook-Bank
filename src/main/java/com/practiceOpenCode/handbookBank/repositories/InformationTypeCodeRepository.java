package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.directories.InformationTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationTypeCodeRepository extends JpaRepository<InformationTypeCode, Long> {
}