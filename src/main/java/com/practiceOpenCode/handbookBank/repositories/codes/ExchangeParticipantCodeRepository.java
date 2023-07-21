package com.practiceOpenCode.handbookBank.repositories.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ExchangeParticipantCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeParticipantCodeRepository extends JpaRepository<ExchangeParticipantCode, Long> {
    Page<ExchangeParticipantCode> findAll(Pageable pageable);
    ExchangeParticipantCode findById(long id);
}