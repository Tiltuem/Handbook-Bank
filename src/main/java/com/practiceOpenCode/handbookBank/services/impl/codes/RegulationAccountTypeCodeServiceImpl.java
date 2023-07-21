package com.practiceOpenCode.handbookBank.services.impl.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.RegulationAccountTypeCode;
import com.practiceOpenCode.handbookBank.repositories.codes.RegulationAccountTypeCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.RegulationAccountTypeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RegulationAccountTypeCodeServiceImpl implements RegulationAccountTypeCodeService {
    @Autowired
    private RegulationAccountTypeCodeRepository repository;

    @Override
    public Page<RegulationAccountTypeCode> getAllCodes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void save(RegulationAccountTypeCode regulationAccountTypeCode) {
        repository.save(regulationAccountTypeCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }

    @Override
    public RegulationAccountTypeCode getViaId(long id) {
        return repository.findById(id);
    }
}
