package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.directories.ParticipantStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipantStatusCodeService {
    List<ParticipantStatusCode> getAllCodes();
    void save (ParticipantStatusCode participantStatusCode);
    void deleteViaId(long id);
}
