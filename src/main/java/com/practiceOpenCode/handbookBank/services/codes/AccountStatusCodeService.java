package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.AccountStatusCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountStatusCodeService {
    Page<AccountStatusCode> getAllCodes(Pageable pageable);
    void save (AccountStatusCode accountStatusCode);
    void deleteViaId(long id);
    AccountStatusCode getViaId(long id);
}
