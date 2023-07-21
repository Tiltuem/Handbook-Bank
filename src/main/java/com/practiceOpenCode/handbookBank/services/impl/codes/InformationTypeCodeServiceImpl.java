package com.practiceOpenCode.handbookBank.services.impl.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.InformationTypeCode;
import com.practiceOpenCode.handbookBank.repositories.codes.InformationTypeCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.InformationTypeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InformationTypeCodeServiceImpl implements InformationTypeCodeService {
    @Autowired
    private InformationTypeCodeRepository repository;

    @Override
    public Page<InformationTypeCode> getAllCodes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void save(InformationTypeCode informationTypeCode) {
        repository.save(informationTypeCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }

    @Override
    public InformationTypeCode getViaId(long id) {
        return repository.findById(id);
    }
}
