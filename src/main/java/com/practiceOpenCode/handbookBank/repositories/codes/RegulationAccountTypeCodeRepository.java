package com.practiceOpenCode.handbookBank.repositories.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.RegulationAccountTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegulationAccountTypeCodeRepository extends JpaRepository<RegulationAccountTypeCode, Long> {
    Page<RegulationAccountTypeCode> findAll(Pageable pageable);
    RegulationAccountTypeCode findById(long id);
}