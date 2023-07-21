package com.practiceOpenCode.handbookBank.repositories.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRestrictionCodeRepository extends JpaRepository<AccountRestrictionCode, Long> {
    Page<AccountRestrictionCode> findAll(Pageable pageable);
    AccountRestrictionCode findById(long id);
}
