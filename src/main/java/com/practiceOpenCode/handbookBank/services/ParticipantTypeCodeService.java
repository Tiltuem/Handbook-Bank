package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.directories.ParticipantTypeCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipantTypeCodeService {
    List<ParticipantTypeCode> getAllCodes();
    void save (ParticipantTypeCode participantTypeCode);
    void deleteViaId(long id);
}
