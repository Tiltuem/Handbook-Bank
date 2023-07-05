package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.directories.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.ParticipantTypeCodeRepository;
import com.practiceOpenCode.handbookBank.services.ParticipantTypeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantTypeCodeServiceImpl implements ParticipantTypeCodeService {
    @Autowired
    private ParticipantTypeCodeRepository repository;

    @Override
    public List<ParticipantTypeCode> getAllCodes() {
        return repository.findAll();
    }

    @Override
    public void save(ParticipantTypeCode participantTypeCode) {
        repository.save(participantTypeCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
