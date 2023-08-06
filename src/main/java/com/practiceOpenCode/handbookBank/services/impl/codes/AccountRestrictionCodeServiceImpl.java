package com.practiceOpenCode.handbookBank.services.impl.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.AccountRestrictionCodeService;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AccountRestrictionCodeServiceImpl implements AccountRestrictionCodeService {
    @Autowired
    private AccountRestrictionCodeRepository repository;

    @Override
    public Page<AccountRestrictionCode> getAllCodes(Pageable pageable, String code, Boolean showDeleted) {
        if (showDeleted != null && showDeleted) {
            if (!Objects.isNull(code)) return repository.findByCodeWithDeleted(pageable, code);
            return repository.findAllWithDeleted(pageable);
        }
        if (!Objects.isNull(code)) return repository.findByCode(pageable, code);

        return repository.findAll(pageable);
    }

    @Override
    public void save(AccountRestrictionCode accountRestrictionCode) {
        repository.save(accountRestrictionCode);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public AccountRestrictionCode getById(long id) {
        return repository.findById(id);
    }

    @Override
    public void recoveryById(long id) {
        AccountRestrictionCode code = repository.findById(id);
        code.setDeleted(false);
        repository.save(code);
    }

    @Override
    public AccountRestrictionCode getByCode(String code) {
        return repository.findByCode(code);
    }
}
