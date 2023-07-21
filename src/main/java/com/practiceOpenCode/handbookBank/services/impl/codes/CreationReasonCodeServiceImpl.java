package com.practiceOpenCode.handbookBank.services.impl.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.CreationReasonCode;
import com.practiceOpenCode.handbookBank.repositories.codes.CreationReasonCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.CreationReasonCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CreationReasonCodeServiceImpl implements CreationReasonCodeService {
    @Autowired
    private CreationReasonCodeRepository repository;

    @Override
    public Page<CreationReasonCode> getAllCodes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void save(CreationReasonCode creationReasonCode) {
        repository.save(creationReasonCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }

    @Override
    public CreationReasonCode getViaId(long id) {
        return repository.findById(id);
    }
}
