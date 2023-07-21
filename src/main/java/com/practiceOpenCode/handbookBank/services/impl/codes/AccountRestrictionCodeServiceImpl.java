package com.practiceOpenCode.handbookBank.services.impl.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.AccountRestrictionCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountRestrictionCodeServiceImpl implements AccountRestrictionCodeService {
    @Autowired
    private AccountRestrictionCodeRepository repository;

    @Override
    public Page<AccountRestrictionCode> getAllCodes(Pageable pageable) {
        return repository.findAll(pageable);
    }
    @Override
    public void save(AccountRestrictionCode accountRestrictionCode) {
        repository.save(accountRestrictionCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }

    @Override
    public AccountRestrictionCode getViaId(long id) {
        return repository.findById(id);
    }
}
