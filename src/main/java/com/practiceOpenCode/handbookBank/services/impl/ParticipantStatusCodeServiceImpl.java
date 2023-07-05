package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.directories.ParticipantStatusCode;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.ParticipantStatusCodeRepository;
import com.practiceOpenCode.handbookBank.services.ParticipantStatusCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipantStatusCodeServiceImpl implements ParticipantStatusCodeService {
    @Autowired
    private ParticipantStatusCodeRepository repository;

    @Override
    public List<ParticipantStatusCode> getAllCodes() {
        return repository.findAll();
    }

    @Override
    public void save(ParticipantStatusCode participantStatusCode) {
        repository.save(participantStatusCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
