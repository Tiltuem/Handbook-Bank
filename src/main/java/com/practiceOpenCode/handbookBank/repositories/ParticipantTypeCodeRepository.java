package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.directories.ParticipantTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantTypeCodeRepository extends JpaRepository<ParticipantTypeCode, Long> {
}