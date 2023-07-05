package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.directories.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.services.AccountRestrictionCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRestrictionCodeServiceImpl implements AccountRestrictionCodeService {
    @Autowired
    private AccountRestrictionCodeRepository repository;

    @Override
    public List<AccountRestrictionCode> getAllCodes() {
        return repository.findAll();
    }

    @Override
    public void save(AccountRestrictionCode accountRestrictionCode) {
        repository.save(accountRestrictionCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
