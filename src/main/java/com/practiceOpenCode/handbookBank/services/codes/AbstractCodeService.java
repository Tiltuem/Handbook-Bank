package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AbstractCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AbstractCodeService<E extends AbstractCode> {
    List<E> getAllCodeList();

    Page<E> getAllCodes(Pageable pageable, String code, Boolean deleted);

    void save(E code);

    void deleteById(long id);

    E getById(long id);

    void recoveryById(long id);

    E getByCode(String code);
}
