package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.Accounts;
import com.practiceOpenCode.handbookBank.models.SWBICs;
import com.practiceOpenCode.handbookBank.repositories.SWBICsRepository;
import com.practiceOpenCode.handbookBank.services.SWBICsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SWBICsServiceImpl implements SWBICsService {
    @Autowired
    private SWBICsRepository repository;

    @Override
    public List<SWBICs> getAllSWBICs() {
        return repository.findAll();
    }

    @Override
    public void save(SWBICs SWBICs) {
        repository.save(SWBICs);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public SWBICs getById(long id) {
        return repository.findById(id);
    }

    @Override
    public void recoveryById(long id) {
        SWBICs swbiCs = repository.findById(id);
        swbiCs.setDeleted(false);
    }
}
