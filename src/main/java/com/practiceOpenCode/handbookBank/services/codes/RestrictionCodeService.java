package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.models.codes.RestrictionCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RestrictionCodeService {
    Page<RestrictionCode> getAllCodes(Pageable pageable);
    void save (RestrictionCode restrictionCode);
    void deleteViaId(long id);
    RestrictionCode getViaId(long id);
}
