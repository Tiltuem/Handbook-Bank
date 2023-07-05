package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.directories.ExchangeParticipantCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeParticipantCodeRepository extends JpaRepository<ExchangeParticipantCode, Long> {
}