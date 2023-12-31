package com.practiceOpenCode.handbookBank.services.codes.impl;

import com.practiceOpenCode.handbookBank.models.codes.AbstractCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AbstractCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.AbstractCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public abstract class AbstractCodeServiceImpl<E extends AbstractCode, T extends AbstractCodeRepository<E>>
        implements AbstractCodeService<E> {
    protected  T repository;

    @Override
    public List<E> getAllCodeList() {
        return repository.findAll();
    }

    @Override
    public Page<E> getAllCodes(Pageable pageable, String code, Boolean deleted) {
        if (!Objects.isNull(deleted) && deleted) {
            if (!Objects.isNull(code)) {
                return repository.findByCodeContaining(pageable, code);
            }

            return repository.findAll(pageable);
        }

        if (!Objects.isNull(code)) {
            return repository.findByCodeContainingAndDeleted(pageable, code, false);
        }

        return repository.findByDeleted(pageable, false);
    }

    @Override
    public void save(E code) {
        repository.save(code);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public E getById(long id) {
        return repository.findById(id);
    }

    @Override
    public void recoveryById(long id) {
        E code = repository.findById(id);
        code.setDeleted(false);

        repository.save(code);
    }

    @Override
    public E getByCode(String code) {
        return repository.findByCode(code);
    }
}
