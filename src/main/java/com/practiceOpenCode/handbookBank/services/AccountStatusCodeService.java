package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Message;
import com.practiceOpenCode.handbookBank.models.directories.AccountStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountStatusCodeService {
    List<AccountStatusCode> getAllCodes();
    void save (AccountStatusCode accountStatusCode);
    void deleteViaId(long id);
}
