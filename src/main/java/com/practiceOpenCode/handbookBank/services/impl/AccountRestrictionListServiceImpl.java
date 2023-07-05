package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.AccountRestrictionList;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionListRepository;
import com.practiceOpenCode.handbookBank.services.AccountRestrictionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRestrictionListServiceImpl implements AccountRestrictionListService {
    @Autowired
    private AccountRestrictionListRepository repository;

    @Override
    public List<AccountRestrictionList> getAllAccounts() {
        return repository.findAll();
    }

    @Override
    public void save(AccountRestrictionList accountRestrictionList) {
        repository.save(accountRestrictionList);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
