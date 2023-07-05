package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.directories.ChangeTypeCode;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.ChangeTypeCodeRepository;
import com.practiceOpenCode.handbookBank.services.ChangeTypeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChangeTypeCodeServiceImpl implements ChangeTypeCodeService {
    @Autowired
    private ChangeTypeCodeRepository repository;

    @Override
    public List<ChangeTypeCode> getAllCodes() {
        return repository.findAll();
    }

    @Override
    public void save(ChangeTypeCode changeTypeCode) {
        repository.save(changeTypeCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
