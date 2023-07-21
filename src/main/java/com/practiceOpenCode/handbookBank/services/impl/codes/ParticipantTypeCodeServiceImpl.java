package com.practiceOpenCode.handbookBank.services.impl.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantTypeCode;
import com.practiceOpenCode.handbookBank.repositories.codes.ParticipantTypeCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.ParticipantTypeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class ParticipantTypeCodeServiceImpl implements ParticipantTypeCodeService {
    @Autowired
    private ParticipantTypeCodeRepository repository;

    @Override
    public Page<ParticipantTypeCode> getAllCodes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void save(ParticipantTypeCode participantTypeCode) {
        repository.save(participantTypeCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }

    @Override
    public ParticipantTypeCode getViaId(long id) {
        return repository.findById(id);
    }
}
