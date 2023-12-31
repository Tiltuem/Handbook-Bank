package com.practiceOpenCode.handbookBank.services.main;

import com.practiceOpenCode.handbookBank.models.main.AccountRestrictionList;

import java.util.List;

public interface AccountRestrictionListService {
    List<AccountRestrictionList> getAllAccounts();

    void save(AccountRestrictionList accountRestrictionList);

    void deleteById(long id);
}
