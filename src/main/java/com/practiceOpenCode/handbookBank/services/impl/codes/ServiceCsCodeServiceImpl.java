package com.practiceOpenCode.handbookBank.services.impl.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.ServiceCsCode;
import com.practiceOpenCode.handbookBank.repositories.codes.ServiceCsCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.ServiceCsCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceCsCodeServiceImpl implements ServiceCsCodeService {
    @Autowired
    private ServiceCsCodeRepository repository;

    @Override
    public Page<ServiceCsCode> getAllCodes(Pageable pageable) {
        return repository.findAll(pageable);
    }
    @Override
    public void save(ServiceCsCode serviceCsCode) {
        repository.save(serviceCsCode);

    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }

    @Override
    public ServiceCsCode getViaId(long id) {
        return repository.findById(id);
    }
}
