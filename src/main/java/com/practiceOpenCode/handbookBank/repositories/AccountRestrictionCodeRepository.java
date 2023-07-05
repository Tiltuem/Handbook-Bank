package com.practiceOpenCode.handbookBank.repositories;

import com.practiceOpenCode.handbookBank.models.directories.AccountRestrictionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRestrictionCodeRepository extends JpaRepository<AccountRestrictionCode, Long> {

    @Override
    <S extends AccountRestrictionCode> S save(S entity);
}
