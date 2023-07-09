package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.directories.AccountStatusCode;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.AccountStatusCodeRepository;
import com.practiceOpenCode.handbookBank.services.AccountStatusCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountStatusCodeServiceImpl implements AccountStatusCodeService {
    @Autowired
    private AccountStatusCodeRepository repository;

    @Override
    public List<AccountStatusCode> getAllCodes() {
        return repository.findAll();
    }

    @Override
    public void save(AccountStatusCode accountStatusCode) {
        repository.save(accountStatusCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }

}
