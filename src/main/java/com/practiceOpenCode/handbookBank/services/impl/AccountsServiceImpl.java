package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.Accounts;
import com.practiceOpenCode.handbookBank.repositories.AccountsRepository;
import com.practiceOpenCode.handbookBank.services.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    private AccountsRepository repository;

    @Override
    public void save(Accounts account) {
        repository.save(account);
    }

    @Override
    public void update(Accounts account) {
        Accounts oldAccount = repository.findById(account.getId());
        account.setAccountRestrictionList(oldAccount.getAccountRestrictionList());
        repository.save(account);
    }


    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Accounts getById(long id) {
        return repository.findById(id);
    }

    @Override
    public void recoveryById(long id) {
        Accounts account = repository.findById(id);
        account.setDeleted(false);
    }
}
