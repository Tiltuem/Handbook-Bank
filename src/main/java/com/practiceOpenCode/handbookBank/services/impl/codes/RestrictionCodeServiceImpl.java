package com.practiceOpenCode.handbookBank.services.impl.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.RestrictionCode;
import com.practiceOpenCode.handbookBank.repositories.codes.RestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.RestrictionCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RestrictionCodeServiceImpl implements RestrictionCodeService {
    @Autowired
    private RestrictionCodeRepository repository;

    @Override
    public Page<RestrictionCode> getAllCodes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void save(RestrictionCode restrictionCode) {
        repository.save(restrictionCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }

    @Override
    public RestrictionCode getViaId(long id) {
        return repository.findById(id);
    }
}
