package com.practiceOpenCode.handbookBank.repositories.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.CreationReasonCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreationReasonCodeRepository extends JpaRepository<CreationReasonCode, Long> {
    Page<CreationReasonCode> findAll(Pageable pageable);
    CreationReasonCode findById(long id);
}
