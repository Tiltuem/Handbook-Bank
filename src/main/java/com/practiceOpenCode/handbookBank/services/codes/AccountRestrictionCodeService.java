package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface AccountRestrictionCodeService {
    Page<AccountRestrictionCode> getAllCodes(Pageable pageable, String code, Boolean showDeleted);
    void save (AccountRestrictionCode accountRestrictionCode);
    void deleteById(long id);
    AccountRestrictionCode getById(long id);
    void recoveryById(long id);
    AccountRestrictionCode getByCode(String code);
}
