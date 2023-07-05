package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.directories.AccountRestrictionCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountRestrictionCodeService {
    List<AccountRestrictionCode> getAllCodes();
    void save (AccountRestrictionCode accountRestrictionCode);
    void deleteViaId(long id);
}
