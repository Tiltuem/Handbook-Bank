package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.directories.RegulationAccountTypeCode;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.RegulationAccountTypeCodeRepository;
import com.practiceOpenCode.handbookBank.services.RegulationAccountTypeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegulationAccountTypeCodeServiceImpl implements RegulationAccountTypeCodeService {
    @Autowired
    private RegulationAccountTypeCodeRepository repository;

    @Override
    public List<RegulationAccountTypeCode> getAllCodes() {
        return repository.findAll();
    }

    @Override
    public void save(RegulationAccountTypeCode regulationAccountTypeCode) {
        repository.save(regulationAccountTypeCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
