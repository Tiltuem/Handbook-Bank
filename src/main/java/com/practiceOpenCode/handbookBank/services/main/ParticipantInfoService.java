package com.practiceOpenCode.handbookBank.services.main;

import com.practiceOpenCode.handbookBank.models.main.ParticipantInfo;
import com.practiceOpenCode.handbookBank.models.main.RestrictionList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ParticipantInfoService {
    Page<ParticipantInfo> getAllParticipants(Pageable pageable);
    void save (ParticipantInfo participantInfo);
    void deleteById(long id);
    void updateById(long id, RestrictionList restrictionList);
}
