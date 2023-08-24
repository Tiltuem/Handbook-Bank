package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Accounts;
import com.practiceOpenCode.handbookBank.models.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountsService {
    void save (Accounts account);
    void update(Accounts account);
    void deleteById(long id);
    Accounts getById(long id);
    void recoveryById(long id);
}
