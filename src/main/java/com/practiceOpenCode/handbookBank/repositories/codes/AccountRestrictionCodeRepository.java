package com.practiceOpenCode.handbookBank.repositories.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import org.hibernate.annotations.Where;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRestrictionCodeRepository extends JpaRepository<AccountRestrictionCode, Long> {
    @Query("SELECT a FROM AccountRestrictionCode a WHERE a.deleted = false")
    Page<AccountRestrictionCode> findAll(Pageable pageable);
    @Query("SELECT a FROM AccountRestrictionCode a WHERE a.deleted = false AND a.code = :code")
    Page<AccountRestrictionCode> findByCode(Pageable pageable, @Param("code") String code);

    @Query("SELECT a FROM AccountRestrictionCode a WHERE a.id = :id")
    AccountRestrictionCode findById(@Param("id") long id);

    @Query("SELECT a FROM AccountRestrictionCode a ")
    Page<AccountRestrictionCode> findAllWithDeleted(Pageable pageable);
    @Query("SELECT a FROM AccountRestrictionCode a WHERE a.code = :code")
    Page<AccountRestrictionCode> findByCodeWithDeleted(Pageable pageable, String code);

    @Query("SELECT a FROM AccountRestrictionCode a WHERE a.deleted = false AND a.code = :code")
    AccountRestrictionCode findByCode(@Param("code") String code);
}
