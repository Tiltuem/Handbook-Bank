package com.practiceOpenCode.handbookBank.services.impl.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ExchangeParticipantCode;
import com.practiceOpenCode.handbookBank.repositories.codes.ExchangeParticipantCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.ExchangeParticipantCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExchangeParticipantCodeServiceImpl implements ExchangeParticipantCodeService {
    @Autowired
    private ExchangeParticipantCodeRepository repository;

    @Override
    public Page<ExchangeParticipantCode> getAllCodes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void save(ExchangeParticipantCode exchangeParticipantCode) {
        repository.save(exchangeParticipantCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }

    @Override
    public ExchangeParticipantCode getViaId(long id) {
        return repository.findById(id);
    }
}
