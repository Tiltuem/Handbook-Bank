package com.practiceOpenCode.handbookBank.services.main;

import com.practiceOpenCode.handbookBank.models.main.AccountRestrictionList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountRestrictionListService {
    List<AccountRestrictionList> getAllAccounts();
    void save (AccountRestrictionList accountRestrictionList);
    void deleteById(long id);
}
