package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.directories.ExchangeParticipantCode;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.ExchangeParticipantCodeRepository;
import com.practiceOpenCode.handbookBank.services.ExchangeParticipantCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeParticipantCodeServiceImpl implements ExchangeParticipantCodeService {
    @Autowired
    private ExchangeParticipantCodeRepository repository;

    @Override
    public List<ExchangeParticipantCode> getAllCodes() {
        return repository.findAll();
    }

    @Override
    public void save(ExchangeParticipantCode exchangeParticipantCode) {
        repository.save(exchangeParticipantCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
