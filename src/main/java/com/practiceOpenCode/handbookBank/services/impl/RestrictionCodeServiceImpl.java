package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.directories.RestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.RestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.services.RestrictionCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestrictionCodeServiceImpl implements RestrictionCodeService {
    @Autowired
    private RestrictionCodeRepository repository;

    @Override
    public List<RestrictionCode> getAllCodes() {
        return repository.findAll();
    }

    @Override
    public void save(RestrictionCode restrictionCode) {
        repository.save(restrictionCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
