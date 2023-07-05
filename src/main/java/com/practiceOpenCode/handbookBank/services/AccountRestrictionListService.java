package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.AccountRestrictionList;
import com.practiceOpenCode.handbookBank.models.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountRestrictionListService {
    List<AccountRestrictionList> getAllAccounts();
    void save (AccountRestrictionList accountRestrictionList);
    void deleteViaId(long id);
}
