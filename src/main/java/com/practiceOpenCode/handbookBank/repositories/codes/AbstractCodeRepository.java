package com.practiceOpenCode.handbookBank.repositories.codes;

import com.practiceOpenCode.handbookBank.models.codes.AbstractCode;
import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface AbstractCodeRepository<E extends AbstractCode> extends JpaRepository<E, Long> {

    Page<E> findAll(Pageable pageable);

    Page<E> findByCodeContainingAndDeleted(Pageable pageable, String code, Boolean deleted);

    E findById(long id);

    Page<E> findByDeleted(Pageable pageable, Boolean deleted);

    Page<E> findByCodeContaining(Pageable pageable, String code);

    E findByCode(String code);
}