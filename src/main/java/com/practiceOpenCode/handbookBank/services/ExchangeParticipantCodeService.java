package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.directories.ExchangeParticipantCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExchangeParticipantCodeService {
    List<ExchangeParticipantCode> getAllCodes();
    void save (ExchangeParticipantCode exchangeParticipantCode);
    void deleteViaId(long id);
}
