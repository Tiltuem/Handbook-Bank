package com.practiceOpenCode.handbookBank.repositories.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.InformationTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationTypeCodeRepository extends JpaRepository<InformationTypeCode, Long> {
    Page<InformationTypeCode> findAll(Pageable pageable);
    InformationTypeCode findById(long id);
}