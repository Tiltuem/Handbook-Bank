package com.practiceOpenCode.handbookBank.services.main;

import com.practiceOpenCode.handbookBank.models.main.Accounts;

public interface AccountsService {
    void save(Accounts account);

    void update(Accounts account);

    void deleteById(long id);

    Accounts getById(long id);

    void recoveryById(long id);
}
