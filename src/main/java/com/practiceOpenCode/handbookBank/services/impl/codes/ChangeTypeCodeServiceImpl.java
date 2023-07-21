package com.practiceOpenCode.handbookBank.services.impl.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ChangeTypeCode;
import com.practiceOpenCode.handbookBank.repositories.codes.ChangeTypeCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.ChangeTypeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ChangeTypeCodeServiceImpl implements ChangeTypeCodeService {
    @Autowired
    private ChangeTypeCodeRepository repository;

    @Override
    public Page<ChangeTypeCode> getAllCodes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void save(ChangeTypeCode changeTypeCode) {
        repository.save(changeTypeCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }

    @Override
    public ChangeTypeCode getViaId(long id) {
        return repository.findById(id);
    }
}
