package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.directories.InformationTypeCode;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.InformationTypeCodeRepository;
import com.practiceOpenCode.handbookBank.services.InformationTypeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InformationTypeCodeServiceImpl implements InformationTypeCodeService {
    @Autowired
    private InformationTypeCodeRepository repository;

    @Override
    public List<InformationTypeCode> getAllCodes() {
        return repository.findAll();
    }

    @Override
    public void save(InformationTypeCode informationTypeCode) {
        repository.save(informationTypeCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
