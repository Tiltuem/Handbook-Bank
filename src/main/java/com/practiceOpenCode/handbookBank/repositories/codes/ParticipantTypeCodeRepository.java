package com.practiceOpenCode.handbookBank.repositories.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantTypeCodeRepository extends JpaRepository<ParticipantTypeCode, Long> {
    Page<ParticipantTypeCode> findAll(Pageable pageable);
    ParticipantTypeCode findById(long id);
}