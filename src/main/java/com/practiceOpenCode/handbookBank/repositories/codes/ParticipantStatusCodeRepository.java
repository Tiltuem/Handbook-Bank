package com.practiceOpenCode.handbookBank.repositories.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantStatusCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantStatusCodeRepository extends JpaRepository<ParticipantStatusCode, Long> {
    Page<ParticipantStatusCode> findAll(Pageable pageable);
    ParticipantStatusCode findById(long id);
}