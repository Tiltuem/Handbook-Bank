package com.practiceOpenCode.handbookBank.services;

import com.practiceOpenCode.handbookBank.models.Accounts;
import com.practiceOpenCode.handbookBank.models.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountsService {
    List<Accounts> getAllAccounts();
    void save (Accounts accounts);
    void deleteViaId(long id);
}
