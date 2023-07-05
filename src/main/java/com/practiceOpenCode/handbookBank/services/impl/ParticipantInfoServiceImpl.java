package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.ParticipantInfo;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.ParticipantInfoRepository;
import com.practiceOpenCode.handbookBank.services.ParticipantInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantInfoServiceImpl implements ParticipantInfoService {
    @Autowired
    private ParticipantInfoRepository repository;

    @Override
    public List<ParticipantInfo> getAllParticipantInfo() {
        return repository.findAll();
    }

    @Override
    public void save(ParticipantInfo participantInfo) {
        repository.save(participantInfo);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
