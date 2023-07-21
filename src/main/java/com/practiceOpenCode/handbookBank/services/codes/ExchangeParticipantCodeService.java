package com.practiceOpenCode.handbookBank.services.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ExchangeParticipantCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExchangeParticipantCodeService {
    Page<ExchangeParticipantCode> getAllCodes(Pageable pageable);
    void save (ExchangeParticipantCode exchangeParticipantCode);
    void deleteViaId(long id);
    ExchangeParticipantCode getViaId(long id);
}
