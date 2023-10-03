package com.practiceOpenCode.handbookBank.services.main.impl;

import com.practiceOpenCode.handbookBank.models.main.ParticipantInfo;
import com.practiceOpenCode.handbookBank.models.main.RestrictionList;
import com.practiceOpenCode.handbookBank.repositories.main.ParticipantInfoRepository;
import com.practiceOpenCode.handbookBank.services.main.ParticipantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ParticipantInfoServiceImpl implements ParticipantInfoService {
    @Autowired
    private ParticipantInfoRepository repository;

    @Override
    public Page<ParticipantInfo> getAllParticipants(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void save(ParticipantInfo participantInfo) {
        repository.save(participantInfo);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public void updateById(long id, RestrictionList restrictionList) {
        ParticipantInfo participantInfo = repository.findById(id);
        participantInfo.getRestrictionList().add(restrictionList);

        repository.save(participantInfo);
    }
}
