package com.practiceOpenCode.handbookBank.services.impl.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ParticipantStatusCode;
import com.practiceOpenCode.handbookBank.repositories.codes.ParticipantStatusCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.ParticipantStatusCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ParticipantStatusCodeServiceImpl implements ParticipantStatusCodeService {
    @Autowired
    private ParticipantStatusCodeRepository repository;

    @Override
    public Page<ParticipantStatusCode> getAllCodes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void save(ParticipantStatusCode participantStatusCode) {
        repository.save(participantStatusCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }

    @Override
    public ParticipantStatusCode getViaId(long id) {
        return repository.findById(id);
    }
}
