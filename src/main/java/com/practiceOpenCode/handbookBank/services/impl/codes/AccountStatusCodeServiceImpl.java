package com.practiceOpenCode.handbookBank.services.impl.codes;

import com.practiceOpenCode.handbookBank.models.codes.AccountRestrictionCode;
import com.practiceOpenCode.handbookBank.models.codes.AccountStatusCode;
import com.practiceOpenCode.handbookBank.repositories.codes.AccountStatusCodeRepository;
import com.practiceOpenCode.handbookBank.services.codes.AccountStatusCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AccountStatusCodeServiceImpl implements AccountStatusCodeService {
    @Autowired
    private AccountStatusCodeRepository repository;

    @Override
    public Page<AccountStatusCode> getAllCodes(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void save(AccountStatusCode accountStatusCode) {
        repository.save(accountStatusCode);
    }

    @Override
    public void deleteViaId(long id) {
        repository.deleteById(id);
    }

    @Override
    public AccountStatusCode getViaId(long id) {
        return repository.findById(id);
    }

}
