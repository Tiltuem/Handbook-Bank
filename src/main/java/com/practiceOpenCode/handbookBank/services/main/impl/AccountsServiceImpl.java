package com.practiceOpenCode.handbookBank.services.main.impl;

import com.practiceOpenCode.handbookBank.models.main.Accounts;
import com.practiceOpenCode.handbookBank.repositories.main.AccountsRepository;
import com.practiceOpenCode.handbookBank.services.main.AccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountsServiceImpl implements AccountsService {
    private final AccountsRepository repository;

    @Override
    public void save(Accounts account) {
        repository.save(account);
    }

    @Override
    public void update(Accounts account) {
        Accounts oldAccount = repository.findById(account.getId());
        account.setAccountRestrictionList(oldAccount.getAccountRestrictionList());
        account.setCreatedBy(oldAccount.getCreatedBy());
        account.setCreatedDate(oldAccount.getCreatedDate());
        account.setModifiedBy(oldAccount.getModifiedBy());
        account.setModifiedDate(oldAccount.getModifiedDate());

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

        repository.save(account);
    }
}
