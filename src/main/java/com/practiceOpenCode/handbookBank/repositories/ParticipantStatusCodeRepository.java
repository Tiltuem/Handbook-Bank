package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.directories.ParticipantStatusCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantStatusCodeRepository extends JpaRepository<ParticipantStatusCode, Long> {
}