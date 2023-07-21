package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipantTypeCodeService {
    Page<ParticipantTypeCode> getAllCodes(Pageable pageable);
    void save (ParticipantTypeCode participantTypeCode);
    void deleteViaId(long id);
    ParticipantTypeCode getViaId(long id);

}
