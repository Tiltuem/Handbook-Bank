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
    public List<Accounts> getAllAccounts() {
        return repository.findAll();
    }

    @Override
    public void save(Accounts accounts) {
        repository.save(accounts);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
