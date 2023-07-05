package com.practiceOpenCode.handbookBank.services.impl;

import com.practiceOpenCode.handbookBank.models.directories.ServiceCsCode;
import com.practiceOpenCode.handbookBank.repositories.AccountRestrictionCodeRepository;
import com.practiceOpenCode.handbookBank.repositories.ServiceCsCodeRepository;
import com.practiceOpenCode.handbookBank.services.ServiceCsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceCsCodeServiceImpl implements ServiceCsCodeService {
    @Autowired
    private ServiceCsCodeRepository repository;

    @Override
    public List<ServiceCsCode> getAllCodes() {
        return repository.findAll();
    }

    @Override
    public void save(ServiceCsCode serviceCsCode) {
        repository.save(serviceCsCode);

    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }
}
