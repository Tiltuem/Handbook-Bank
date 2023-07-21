package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.CreationReasonCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreationReasonCodeService {
    Page<CreationReasonCode> getAllCodes(Pageable pageable);
    void save (CreationReasonCode creationReasonCode);
    void deleteViaId(long id);
    CreationReasonCode getViaId(long id);
}
