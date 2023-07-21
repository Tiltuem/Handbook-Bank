package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ChangeTypeCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChangeTypeCodeService {
    Page<ChangeTypeCode> getAllCodes(Pageable pageable);
    void save (ChangeTypeCode changeTypeCode);
    void deleteViaId(long id);
    ChangeTypeCode getViaId(long id);
}
