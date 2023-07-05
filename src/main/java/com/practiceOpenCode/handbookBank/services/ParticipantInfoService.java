package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.ParticipantInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipantInfoService {
    List<ParticipantInfo> getAllParticipantInfo();
    void save (ParticipantInfo participantInfo);
    void deleteViaId(long id);
}
