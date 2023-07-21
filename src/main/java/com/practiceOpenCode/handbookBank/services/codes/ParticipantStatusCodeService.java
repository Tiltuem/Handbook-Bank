package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantStatusCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipantStatusCodeService {
    Page<ParticipantStatusCode> getAllCodes(Pageable pageable);
    void save (ParticipantStatusCode participantStatusCode);
    void deleteViaId(long id);
    ParticipantStatusCode getViaId(long id);
}
