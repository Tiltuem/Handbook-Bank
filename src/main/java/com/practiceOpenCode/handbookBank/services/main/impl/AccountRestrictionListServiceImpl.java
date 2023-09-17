package com.practiceOpenCode.handbookBank.services.main.impl;

import com.practiceOpenCode.handbookBank.models.main.AccountRestrictionList;
import com.practiceOpenCode.handbookBank.repositories.main.AccountRestrictionListRepository;
import com.practiceOpenCode.handbookBank.services.main.AccountRestrictionListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
