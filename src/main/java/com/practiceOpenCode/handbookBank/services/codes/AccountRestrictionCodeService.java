package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountRestrictionCodeService {
    Page<AccountRestrictionCode> getAllCodes(Pageable pageable);
    void save (AccountRestrictionCode accountRestrictionCode);
    void deleteViaId(long id);
    AccountRestrictionCode getViaId(long id);
}
