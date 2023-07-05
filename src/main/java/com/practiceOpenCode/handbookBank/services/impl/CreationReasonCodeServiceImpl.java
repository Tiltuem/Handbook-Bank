package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.directories.CreationReasonCode;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.CreationReasonCodeRepository;
import com.practiceOpenCode.handbookBank.services.CreationReasonCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreationReasonCodeServiceImpl implements CreationReasonCodeService {
    @Autowired
    private CreationReasonCodeRepository repository;

    @Override
    public List<CreationReasonCode> getAllCodes() {
        return repository.findAll();
    }

    @Override
    public void save(CreationReasonCode creationReasonCode) {
        repository.save(creationReasonCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
