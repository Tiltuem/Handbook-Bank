package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.ParticipantInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParticipantInfoService {
    Page<ParticipantInfo> getAllParticipants(Pageable pageable);
    void save (ParticipantInfo participantInfo);
    void deleteViaId(long id);
}
