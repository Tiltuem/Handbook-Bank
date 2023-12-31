package com.practiceOpenCode.handbookBank.services.main.impl;

import com.practiceOpenCode.handbookBank.models.main.AccountRestrictionList;
import com.practiceOpenCode.handbookBank.repositories.main.AccountRestrictionListRepository;
import com.practiceOpenCode.handbookBank.services.main.AccountRestrictionListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountRestrictionListServiceImpl implements AccountRestrictionListService {
    private final AccountRestrictionListRepository repository;

    @Override
    public List<AccountRestrictionList> getAllAccounts() {
        return repository.findAll();
    }

    @Override
    public void save(AccountRestrictionList accountRestrictionList) {
        repository.save(accountRestrictionList);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
